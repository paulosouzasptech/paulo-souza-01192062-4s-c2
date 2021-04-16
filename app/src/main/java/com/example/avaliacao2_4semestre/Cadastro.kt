package com.example.avaliacao2_4semestre

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.avaliacao2_4semestre.api_cachorros.ApiConexao
import com.example.avaliacao2_4semestre.api_cachorros.Cachorro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Cadastro: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastrar_cachorro_activity)
    }

    fun criarCachorro(view: View) {
        val apiFilmes = ApiConexao.criar()

        val racaValue:TextView = findViewById(R.id.et_raca)
        val precoMedioValue:TextView = findViewById(R.id.et_preco_medio)
        val indicadoCrianca:Switch = findViewById(R.id.sw_indicado_crianca)

        val racaText:String = racaValue.text.toString()
        val precoMedioDouble:Double =  precoMedioValue.text.toString().toDouble()
        val indicadoCriancaBoolean:Boolean = indicadoCrianca.isChecked

        val cachorroParaCriar = Cachorro(racaText, precoMedioDouble, indicadoCriancaBoolean)

        val sucessoLayout: TextView = findViewById(R.id.tv_cadastro_sucesso)
        val cachorroFeliz: ImageView = findViewById(R.id.iv_cachorro_feliz)

        apiFilmes.post(cachorroParaCriar).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 201) {
                    sucessoLayout.visibility = View.VISIBLE
                    cachorroFeliz.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}