package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentFoodDetailsBinding
import com.cetinmustafa.foodapp.repo.FoodsDaoRepository

class FoodDetailsFragmentViewModel : ViewModel(){
    val frepo = FoodsDaoRepository()

    fun addToBasket(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String, view:FragmentFoodDetailsBinding){
        frepo.addToBasket(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi, view)
    }
}