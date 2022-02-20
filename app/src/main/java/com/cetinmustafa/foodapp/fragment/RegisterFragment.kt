package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.databinding.FragmentRegisterBinding
import com.cetinmustafa.foodapp.viewmodel.RegisterFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private lateinit var tasarim:FragmentRegisterBinding
    private lateinit var viewModel:RegisterFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        tasarim.registerFragment = this

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RegisterFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.textViewToLogin.setOnClickListener {
            val route = RegisterFragmentDirections.registerToLoginRoute()
            Navigation.findNavController(it).navigate(route)
        }

    }

    fun register(email:String, password:String, name:String){
        if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
            viewModel.register(email, password, name, tasarim)
        } else {
            tasarim.registerEmailAddress.setError("Zorunlu Alan")
            tasarim.registerPassword.setError("Zorunlu Alan")
            tasarim.registerName.setError("Zorunlu Alan")
        }

    }


}