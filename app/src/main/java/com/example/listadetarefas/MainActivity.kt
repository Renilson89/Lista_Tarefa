package com.example.listadetarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadetarefas.adapter.TarefaAdapter
import com.example.listadetarefas.database.TarefaDAO
import com.example.listadetarefas.databinding.ActivityMainBinding
import com.example.listadetarefas.model.Tarefa

class MainActivity : AppCompatActivity() {
    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()
    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        biding.fbAdicionar.setOnClickListener{
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }
        tarefaAdapter = TarefaAdapter(
            {id -> confirmaExclusao(id)},
            { tarefa -> editar(tarefa) }
        )

        biding.rvTarefas.adapter = tarefaAdapter
        biding.rvTarefas.layoutManager = LinearLayoutManager(this)

    }

    private fun editar(tarefa: Tarefa) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }

    private fun confirmaExclusao(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmar Exclusão")
        alertBuilder.setMessage("Deseja realmente excluir a tarefas?")
        alertBuilder.setPositiveButton("Sim"){_, _ ->
            val tarefaDAO = TarefaDAO(this)
            if (tarefaDAO.remover(id)){
                atualizarListaTarefas()
                Toast.makeText(this, "Sucesso ao Excluir",
                Toast.LENGTH_SHORT).show()
            }else{Toast.makeText(this, "Sucesso ao Excluir",
                    Toast.LENGTH_SHORT).show()}

        }
        alertBuilder.setPositiveButton("Não"){_, _ ->}
        alertBuilder.create().show()
    }

    private fun atualizarListaTarefas(){
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.adicionarLista(listaTarefas)
    }

    override fun onStart() {
        super.onStart()
        atualizarListaTarefas()

    }
}