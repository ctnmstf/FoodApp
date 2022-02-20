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
import com.cetinmustafa.foodapp.databinding.FragmentProfileBinding
import com.cetinmustafa.foodapp.viewmodel.ProfileFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var tasarim:FragmentProfileBinding
    private lateinit var viewModel:ProfileFragmentViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        tasarim.profileFragment = this
        tasarim.profileToolbarTitle = "Profil"

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProfileFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasarim.name = auth.currentUser!!.displayName
        tasarim.email = auth.currentUser!!.email

        tasarim.changePassword.setOnClickListener {
            val route = ProfileFragmentDirections.profileToPassword()
            Navigation.findNavController(it).navigate(route)
        }

        tasarim.changeProfile.setOnClickListener {
            val route = ProfileFragmentDirections.profileToChange()
            Navigation.findNavController(it).navigate(route)
        }
    }

    fun logout(){
        viewModel.logout(tasarim)
    }

    override fun onResume() {
        super.onResume()
        auth.currentUser!!.reload()
    }
}