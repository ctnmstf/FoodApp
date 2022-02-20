package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentLoginBinding
import com.cetinmustafa.foodapp.repo.AuthDaoRepository

class LoginFragmentViewModel : ViewModel() {
    val arepo = AuthDaoRepository()

    fun login(email: String, password: String, tasarim: FragmentLoginBinding){
        arepo.login(email, password, tasarim)
    }
}