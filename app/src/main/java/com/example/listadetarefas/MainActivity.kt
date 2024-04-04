package com.example.listadetarefas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listadetarefas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val biding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        biding.fbAdicionar.setOnClickListener{
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }
    }
}