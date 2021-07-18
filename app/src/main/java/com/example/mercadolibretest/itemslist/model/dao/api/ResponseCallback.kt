package com.example.mercadolibretest.itemslist.model.dao.api

import com.example.mercadolibretest.itemslist.event.ItemSearchEvent

interface ResponseCallback {
    fun onResponse(event: ItemSearchEvent)
}