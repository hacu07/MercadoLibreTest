package com.example.mercadolibretest.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Util {
    companion object{
        fun getRetrofit(): Retrofit? {
            return Retrofit.Builder()
                    .baseUrl(Endpoints.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}