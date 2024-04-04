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
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        TODO("Not yet implemented")
    }
}