package com.example.avaliacao2_4semestre

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.avaliacao2_4semestre.api_cachorros.ApiConexao
import com.example.avaliacao2_4semestre.api_cachorros.Cachorro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Listagem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listar_cachorros)

        fun listarCachorros(view: View) {
            val tvCachorroNaoIndicadoParaCriancas: TextView = findViewById(R.id.tv_cachorros_nao_indicados_value)
            val tvCachorroIndicadoParaCriancas: TextView = findViewById(R.id.et_cachorros_indicados_value)
            val tvTotalDeCachorros: TextView = findViewById(R.id.tv_total_cachorros_value)

            val apiConexao = ApiConexao.criar()

            var totalQuantIndicados:Int = 0
            var totalQuantNaoIndicados:Int = 0
            var totalQuantCachorros:Int = 0

            apiConexao.get().enqueue(object : Callback<List<Cachorro>> {

                override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {
                    response.body()?.forEach {
                        if (it.indicadoCriancas) {
                            totalQuantIndicados++
                        } else {
                            totalQuantNaoIndicados++
                        }
                    }

                    totalQuantCachorros = totalQuantIndicados + totalQuantNaoIndicados

                    tvCachorroIndicadoParaCriancas.text = totalQuantIndicados.toString()
                    tvCachorroNaoIndicadoParaCriancas.text = totalQuantNaoIndicados.toString()
                    tvTotalDeCachorros.text = totalQuantCachorros.toString()
                }

                override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                    Toast.makeText(baseContext, "Erro na api: ${t.message!!}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}