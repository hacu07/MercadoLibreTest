package com.example.mercadolibretest.itemslist.model.dao.api

import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemslist.dto.ServiceResponse
import com.example.mercadolibretest.itemslist.event.ItemSearchEvent
import com.example.mercadolibretest.utils.Constants
import com.example.mercadolibretest.utils.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemSearchAPIDao {
    fun searchItems(itemName: String, callback: ResponseCallback){
        val service = Util.getRetrofit()?.create(SearchItemsService::class.java)

        service?.searchItems(itemName)?.enqueue(object : Callback<ServiceResponse>{
            override fun onResponse(call: Call<ServiceResponse>, response: Response<ServiceResponse>) {
                val body = response?.body()

                var event = if(body != null){
                        ItemSearchEvent(
                            typeEvent = if (!body.results.isNullOrEmpty()) Constants.SUCCESS else Constants.DATA_ERROR,
                            results = body.results,
                            message =  if (!body.results.isNullOrEmpty()) R.string.SUCCESS_MESSAGE else R.string.DATA_ERROR_MESSAGE
                        )
                    }else{
                        ItemSearchEvent(
                            typeEvent = Constants.RESPONSE_ERROR,
                            results = null,
                            message =  R.string.DATA_ERROR_MESSAGE
                        )
                    }

                callback.onResponse(event)
            }

            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {
                callback.onResponse(
                    ItemSearchEvent()
                )
            }

        })


    }
}