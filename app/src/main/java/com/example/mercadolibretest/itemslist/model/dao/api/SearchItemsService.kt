package com.example.mercadolibretest.itemslist.model.dao.api

import com.example.mercadolibretest.itemslist.dto.ServiceResponse
import com.example.mercadolibretest.utils.Endpoints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchItemsService {
    @GET(Endpoints.GET_ITEMS)
    fun searchItems(@Query("q") item: String): Call<ServiceResponse>
}