package com.example.cryptoapp.api

import com.example.cryptoapp.model.Crypto
import retrofit2.Call
import retrofit2.http.GET

interface BinanceApi {
    @GET("api/v3/ticker/price")
    fun getPrice() : Call<List<Crypto>>
}