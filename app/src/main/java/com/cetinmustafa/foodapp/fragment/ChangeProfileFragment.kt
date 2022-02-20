package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.databinding.FragmentChangeProfileBinding
import com.cetinmustafa.foodapp.viewmodel.ChangeProfileViewModel

class ChangeProfileFragment : Fragment() {
    private lateinit var tasarim: FragmentChangeProfileBinding
    private lateinit var viewModel: ChangeProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_change_profile, container, false)
        tasarim.changeProfileFragment = this
        tasarim.toolbarTitle = "Bilgileri Güncelle"

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar3)

        tasarim.toolbar3.setNavigationOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ChangeProfileViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun changeProfile(name:String, email:String, password:String){
        if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.changeProfile(name, email, password, tasarim)
        } else {
            tasarim.changeName.setError("Zorunlu Alan")
            tasarim.changeEmail.setError("Zorunlu Alan")
            tasarim.changeProfilePassword.setError("Zorunlu Alan")
        }
    }

}