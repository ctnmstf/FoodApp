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
import com.cetinmustafa.foodapp.databinding.FragmentLandingPageBinding
import com.cetinmustafa.foodapp.viewmodel.LandingPageViewModel
import com.google.firebase.auth.FirebaseAuth

class LandingPageFragment : Fragment() {
    private lateinit var tasarim: FragmentLandingPageBinding
    private lateinit var viewModel: LandingPageViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_landing_page, container, false)
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LandingPageViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.buttonLandingLogin.setOnClickListener {
            val route = LandingPageFragmentDirections.landingToLogin()
            Navigation.findNavController(it).navigate(route)
        }

        tasarim.buttonLandingRegister.setOnClickListener {
            val route = LandingPageFragmentDirections.landingToRegister()
            Navigation.findNavController(it).navigate(route)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val route = LandingPageFragmentDirections.landingToHome()
            Navigation.findNavController(tasarim.root).navigate(route)
        }
    }

}