package com.example.avaliacao2_4semestre

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irCadastro(view: View) {
        val telaCadastro = Intent(this, Cadastro::class.java)

        startActivity(telaCadastro)
    }

    fun irParaListagem(view: View) {
        val telaListagem = Intent(this, Listagem::class.java)

        startActivity(telaListagem)
    }
}