package com.example.mercadolibretest.itemSearch.dto

import java.io.Serializable

data class Item(
    val title: String,
    val price: Double,
    val available_quantity: Int,
    val sold_quantity: Int,
    val thumbnail: String,
    val accepts_mercadopago: Boolean,
    val seller: Seller,
    val attributes: ArrayList<Attribute>
):Serializable

data class Attribute(
    val name: String,
    val value_name: String
):Serializable