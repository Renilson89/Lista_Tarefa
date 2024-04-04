package com.example.listadetarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listadetarefas.database.TarefaDAO
import com.example.listadetarefas.databinding.ActivityAdicionarTarefaBinding
import com.example.listadetarefas.databinding.ActivityMainBinding
import com.example.listadetarefas.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {
   private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            if(binding.editTarefa.text.isNotEmpty()) {
                val descricao = binding.editTarefa.text.toString()
                val tarefa = Tarefa(-1, descricao, "default")
                val tarefaDAO = TarefaDAO(this)
                if (tarefaDAO.salvar(tarefa)){
                    Toast.makeText(
                        this,
                        "Tarefa cadastrada com Sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

            }else{
                Toast.makeText(this,
                "Preencha uma Tarefa",
                Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun salvar() {
        TODO("Not yet implemented")
    }
}