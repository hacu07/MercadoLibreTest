package com.example.mercadolibretest.itemSearch.model.dao.api

import com.example.mercadolibretest.itemSearch.event.ItemSearchEvent

interface ResponseCallback {
    fun onResponse(event: ItemSearchEvent)
}