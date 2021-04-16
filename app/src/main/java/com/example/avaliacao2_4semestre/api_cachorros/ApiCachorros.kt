package com.example.avaliacao2_4semestre.api_cachorros

import retrofit2.Call
import retrofit2.http.*

interface ApiCachorros {

    @GET(value = "/cachorros")
    fun get(): Call<List<Cachorro>>

    @POST(value = "/cachorros")
    fun post(@Body novoCachorro: Cachorro): Call<Cachorro>
}