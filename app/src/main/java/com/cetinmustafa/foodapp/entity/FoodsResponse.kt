package com.cetinmustafa.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsResponse(@SerializedName("yemekler") @Expose var yemekler:List<Foods>,
                         @SerializedName("success") @Expose var success:Int
                         ) {
}