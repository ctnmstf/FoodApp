package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.databinding.FragmentLoginBinding
import com.cetinmustafa.foodapp.viewmodel.LoginFragmentViewModel

class LoginFragment : Fragment() {
    private lateinit var tasarim:FragmentLoginBinding
    private lateinit var viewModel:LoginFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        tasarim.loginFragment = this

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LoginFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasarim.textViewToRegister.setOnClickListener {
            val route = LoginFragmentDirections.loginToRegisterRoute()
            Navigation.findNavController(it).navigate(route)
        }
    }

    fun login(email:String, password:String){
        if(email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password, tasarim)
        } else {
            tasarim.loginEmailAddress.setError("Zorunlu Alan")
            tasarim.loginPassword.setError("Zorunlu Alan")
        }
    }

}