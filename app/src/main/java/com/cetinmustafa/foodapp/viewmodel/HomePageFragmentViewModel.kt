package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.entity.Foods
import com.cetinmustafa.foodapp.repo.FoodsDaoRepository

class HomePageFragmentViewModel : ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()
    val frepo = FoodsDaoRepository()

    init {
        loadFoods()
        foodList = frepo.getFoods()
    }

    fun loadFoods(){
        frepo.getAllFoods()
    }

    fun search(searchText:String){
        frepo.searchFood(searchText)
    }
}