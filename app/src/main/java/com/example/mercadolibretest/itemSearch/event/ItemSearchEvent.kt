package com.example.mercadolibretest.itemSearch.event

import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemSearch.dto.Item
import com.example.mercadolibretest.itemSearch.dto.Result
import com.example.mercadolibretest.utils.Constants
import java.io.Serializable

data class ItemSearchEvent(
        var typeEvent: Int = Constants.CONNECTION_ERROR,
        val items: ArrayList<Item>? = null,
        val message: Int = R.string.CONNECTION_ERROR_MESSAGE
): Serializable