package com.example.mercadolibretest.itemslist.event

import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemslist.dto.Item
import com.example.mercadolibretest.itemslist.dto.Result
import com.example.mercadolibretest.utils.Constants
import java.io.Serializable

data class ItemSearchEvent(
    var typeEvent: Int = Constants.CONNECTION_ERROR,
    val results: ArrayList<Result>? = null,
    val message: Int = R.string.CONNECTION_ERROR_MESSAGE
): Serializable