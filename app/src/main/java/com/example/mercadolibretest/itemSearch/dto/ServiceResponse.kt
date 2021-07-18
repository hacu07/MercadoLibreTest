package com.example.mercadolibretest.itemSearch.dto

import java.io.Serializable

data class ServiceResponse(
    val results: ArrayList<Item>
): Serializable