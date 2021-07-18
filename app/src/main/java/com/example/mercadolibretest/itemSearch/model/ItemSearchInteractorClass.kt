package com.example.mercadolibretest.itemSearch.model

import com.example.mercadolibretest.itemSearch.event.ItemSearchEvent
import com.example.mercadolibretest.itemSearch.model.dao.api.ItemSearchAPIDao
import com.example.mercadolibretest.itemSearch.model.dao.api.ResponseCallback
import org.greenrobot.eventbus.EventBus

class ItemSearchInteractorClass : ItemSearchInteractor {

    private lateinit var mAPIDao: ItemSearchAPIDao

    constructor(){
        mAPIDao = ItemSearchAPIDao()
    }

    override fun searchItems(itemName: String) {
        mAPIDao.searchItems(itemName, object : ResponseCallback{
            override fun onResponse(event: ItemSearchEvent) {
                postEvent(event)
            }
        })
    }

    public fun postEvent(event: ItemSearchEvent) {
        EventBus.getDefault().post(event)
    }

}