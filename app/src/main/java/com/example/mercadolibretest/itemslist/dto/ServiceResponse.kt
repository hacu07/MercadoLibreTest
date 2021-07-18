package com.example.mercadolibretest.itemslist.dto

import java.io.Serializable

data class ServiceResponse(
    val results: ArrayList<Result>
): Serializable