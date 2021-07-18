package com.example.mercadolibretest.itemSearch.view

import com.example.mercadolibretest.itemSearch.dto.Item

interface ItemSearchView {
    fun showProgress()
    fun hideProgress()
    fun showRecycler()
    fun hideRecycler()
    fun showMessage(message: Int)
    fun loadItems(items: ArrayList<Item>)
    fun showToast(message: Int)
    fun clearItems()
    fun hideMessageView()
    fun enableViewElements()
    fun disableViewElements()
}