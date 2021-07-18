package com.example.mercadolibretest.itemSearch

import com.example.mercadolibretest.itemSearch.event.ItemSearchEvent

interface ItemSearchPresenter {
    fun onCreate()
    fun onDestroy()
    fun searchItems(itemName: String)
    fun onEventListener(event: ItemSearchEvent)
}