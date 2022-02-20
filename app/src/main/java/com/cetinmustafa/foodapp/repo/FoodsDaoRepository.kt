package com.cetinmustafa.foodapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cetinmustafa.foodapp.databinding.FragmentFoodDetailsBinding
import com.cetinmustafa.foodapp.entity.*
import com.cetinmustafa.foodapp.retrofit.ApiUtils
import com.cetinmustafa.foodapp.retrofit.FoodsDaoInterface
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsDaoRepository {
    var foodList: MutableLiveData<List<Foods>>
    var basketFoodList: MutableLiveData<List<BasketFoods>>

    var fdao: FoodsDaoInterface

    init {
        fdao = ApiUtils.getFoodsDaoInterface()
        foodList = MutableLiveData()
        basketFoodList = MutableLiveData()
    }

    fun getFoods(): MutableLiveData<List<Foods>> {
        return foodList
    }

    fun getAllFoods() {
        fdao.allFoods().enqueue(object : Callback<FoodsResponse> {
            override fun onResponse(
                call: Call<FoodsResponse>,
                response: Response<FoodsResponse>
            ) {
                val list = response.body().yemekler
                foodList.value = list
            }

            override fun onFailure(call: Call<FoodsResponse>?, t: Throwable?) {
                Log.e("Error", "Veriler çekilemedi")
            }
        })
    }

    fun addToBasket(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String,
        view: FragmentFoodDetailsBinding
    ) {
        fdao.addToBasket(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            .enqueue(object : Callback<CRUDResponse> {
                override fun onResponse(
                    call: Call<CRUDResponse>?,
                    response: Response<CRUDResponse>?
                ) {
                    Snackbar.make(view.root, "Sepete eklendi", Snackbar.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                    Log.e("Basket", "Hata")
                    Snackbar.make(view.root, "Tekrar deneyiniz", Snackbar.LENGTH_SHORT).show()
                }
            })
    }

    fun getBasketFoods(): MutableLiveData<List<BasketFoods>>{
        return basketFoodList
    }

    fun getAllBasketFood(kullanici_adi: String) {
        fdao.getBasketFoods(kullanici_adi).enqueue(object : Callback<BasketResponse> {
            override fun onResponse(
                call: Call<BasketResponse>,
                foods: Response<BasketResponse>
            ) {
                val list = foods.body().sepet_yemekler
                basketFoodList.value = list

            }

            override fun onFailure(call: Call<BasketResponse>?, t: Throwable?) {}
        })
    }

    fun deleteFood(sepet_yemek_id:Int, kullanici_adi: String){
        fdao.deleteFood(sepet_yemek_id, kullanici_adi).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                getAllBasketFood(kullanici_adi)
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {}
        })
    }

    fun searchFood(searchText: String) {
        if (searchText != "") {
            val filteredList = foodList.value!!.filter { foods ->
                foods.yemek_adi.lowercase().contains(searchText.lowercase())
            }
            if (filteredList.isEmpty()) {
                Log.e("Arama", "Olmadı")
            } else {
                Log.e("Arama", "Oldu")
                foodList.value = filteredList
            }
        } else {
            getAllFoods()
        }
    }
}