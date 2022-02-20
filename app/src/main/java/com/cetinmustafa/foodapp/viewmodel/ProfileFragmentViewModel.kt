package com.cetinmustafa.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.cetinmustafa.foodapp.databinding.FragmentProfileBinding
import com.cetinmustafa.foodapp.repo.AuthDaoRepository

class ProfileFragmentViewModel : ViewModel() {
    val arepo = AuthDaoRepository()

    fun logout(view: FragmentProfileBinding){
        arepo.logout(view)
    }
}
