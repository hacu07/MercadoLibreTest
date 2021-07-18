package com.example.mercadolibretest.utils

import android.content.Context
import android.widget.Toast
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

        fun showToast(context: Context, message: Int) {
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }
}