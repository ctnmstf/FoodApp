package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentChangePasswordBinding
import com.cetinmustafa.foodapp.repo.AuthDaoRepository

class ChangePasswordViewModel : ViewModel() {
    val arepo = AuthDaoRepository()

    fun changePassword(current_password:String, new_password:String, view: FragmentChangePasswordBinding){
        arepo.changePassword(current_password, new_password, view)
    }
}