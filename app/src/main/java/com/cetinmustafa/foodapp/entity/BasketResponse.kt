package com.cetinmustafa.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("sepet_yemekler") @Expose var sepet_yemekler: List<BasketFoods>,
    @SerializedName("success") @Expose var success: Int
) {
}