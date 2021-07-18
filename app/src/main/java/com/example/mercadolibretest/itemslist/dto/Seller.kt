package com.example.mercadolibretest.itemslist.dto

import java.io.Serializable

data class Seller(
    val permalink: String?,
    val seller_reputation: SellerReputation?
): Serializable

data class SellerReputation(
    val transactions: Transaction?
):Serializable

data class Transaction(
    val ratings: Rating?
): Serializable

data class Rating(
    val negative: Float,
    val positive: Float,
    val neutral: Float
): Serializable
