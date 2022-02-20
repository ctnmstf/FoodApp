package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.entity.BasketFoods
import com.cetinmustafa.foodapp.repo.FoodsDaoRepository
import com.google.firebase.auth.FirebaseAuth

class BasketFragmentViewModel : ViewModel() {
    var foodList = MutableLiveData<List<BasketFoods>>()
    val frepo = FoodsDaoRepository()
    var auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
        loadBasketFoods(auth.currentUser!!.email!!)
        foodList = frepo.getBasketFoods()
    }

    fun loadBasketFoods(kullanici_adi:String){
        frepo.getAllBasketFood(kullanici_adi)
    }

    fun deleteFood(sepet_yemek_id:Int, kullanici_adi: String){
        frepo.deleteFood(sepet_yemek_id, kullanici_adi)
    }
}