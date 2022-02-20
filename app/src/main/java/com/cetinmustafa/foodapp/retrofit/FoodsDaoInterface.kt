package com.cetinmustafa.foodapp.retrofit

import com.cetinmustafa.foodapp.entity.BasketFoods
import com.cetinmustafa.foodapp.entity.BasketResponse
import com.cetinmustafa.foodapp.entity.CRUDResponse
import com.cetinmustafa.foodapp.entity.FoodsResponse
import retrofit2.Call
import retrofit2.http.*

interface FoodsDaoInterface {

    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods(): Call<FoodsResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addToBasket(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String,
    ): Call<CRUDResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getBasketFoods(
        @Field("kullanici_adi") kullanici_adi: String
    ) : Call<BasketResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFood(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String,
    ) : Call<CRUDResponse>
}