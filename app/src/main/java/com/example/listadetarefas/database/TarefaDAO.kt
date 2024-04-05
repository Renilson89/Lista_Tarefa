package com.example.listadetarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listadetarefas.model.Tarefa

class TarefaDAO(context: Context): ITarefaDAO {
    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {
       val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)
        try {
            escrita.insert(DatabaseHelper.NOME_TABELAS_TAREFAS, null, conteudos)
            Log.i("info_db", "Sucesso ao Salvar")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Salvar")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        val args = arrayOf(tarefa.idTarefa.toString())
        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)
        try {
            escrita.update(
                DatabaseHelper.NOME_TABELAS_TAREFAS,
                conteudo,
                "${DatabaseHelper.COLUNA_ID_TAREFAS} = ? ",
                args
            )
            Log.i("info_db", "Sucesso ao Atualizar")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Atualizar")
            return false
        }
        return true
    }


    override fun remover(idTarefa: Int): Boolean {
        val args = arrayOf(idTarefa.toString())
        try {
            escrita.delete(
                DatabaseHelper.NOME_TABELAS_TAREFAS,
                "${DatabaseHelper.COLUNA_ID_TAREFAS} = ? ",
                args
            )
            Log.i("info_db", "Sucesso ao Excluir")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Excluir")
            return false
        }
        return true
    }


    override fun listar(): List<Tarefa> {
        val listaTarefas = mutableListOf<Tarefa>()
        val sql = "SELECT ${DatabaseHelper.COLUNA_ID_TAREFAS}, " +
                "${DatabaseHelper.COLUNA_DESCRICAO}, strftime('%d/%m/%Y %H:%M', " +
                "${DatabaseHelper.COLUNA_DATA_CADASTRO} ) " +
                "FROM ${DatabaseHelper.NOME_TABELAS_TAREFAS}"

        return listaTarefas
    }
}