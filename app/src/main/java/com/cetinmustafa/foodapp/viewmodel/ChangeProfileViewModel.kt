package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentChangeProfileBinding
import com.cetinmustafa.foodapp.repo.AuthDaoRepository

class ChangeProfileViewModel : ViewModel() {
    val arepo = AuthDaoRepository()

    fun changeProfile(name:String, email:String, password:String, view: FragmentChangeProfileBinding){
        arepo.changeProfile(name, email, password, view)
    }
}