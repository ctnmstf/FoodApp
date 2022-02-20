package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentRegisterBinding
import com.cetinmustafa.foodapp.repo.AuthDaoRepository

class RegisterFragmentViewModel :ViewModel() {
    val arepo = AuthDaoRepository()

    fun register(email:String, password:String, name:String, view: FragmentRegisterBinding){
        arepo.register(email, password, name, view)
    }
}