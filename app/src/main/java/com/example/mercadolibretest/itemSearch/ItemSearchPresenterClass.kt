package com.example.mercadolibretest.itemSearch

import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemSearch.event.ItemSearchEvent
import com.example.mercadolibretest.itemSearch.model.ItemSearchInteractor
import com.example.mercadolibretest.itemSearch.model.ItemSearchInteractorClass
import com.example.mercadolibretest.itemSearch.view.ItemSearchView
import com.example.mercadolibretest.itemSearch.view.MainActivity
import com.example.mercadolibretest.utils.Constants
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ItemSearchPresenterClass : ItemSearchPresenter {

    private var mView: ItemSearchView? = null
    private lateinit var mInteractor: ItemSearchInteractor

    constructor(mView: MainActivity){
        this.mView = mView
        mInteractor = ItemSearchInteractorClass()
    }


    override fun onCreate() {
        mView.let {
            EventBus.getDefault().register(this)
        }
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    override fun searchItems(itemName: String) {
        mView.let {
            if (!itemName.isNullOrEmpty()){
                it?.showProgress()
                mInteractor.searchItems(getItemNameToSearch(itemName.trim()))
            }else{
                it?.showMessage(R.string.empty_text_message)
            }
        }
    }

    private fun getItemNameToSearch(itemName: String): String {
        return itemName.replace(" ","%20")
    }

    @Subscribe
    override fun onEventListener(event: ItemSearchEvent) {
        mView.let {
            it?.hideProgress()
            when(event.typeEvent){
                Constants.SUCCESS -> it?.loadItems(event.items!!)
                else -> it?.showMessage(event.message)
            }
        }
    }
}