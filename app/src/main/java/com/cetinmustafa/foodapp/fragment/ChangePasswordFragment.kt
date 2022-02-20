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
import com.cetinmustafa.foodapp.databinding.FragmentChangePasswordBinding
import com.cetinmustafa.foodapp.viewmodel.ChangePasswordViewModel

class ChangePasswordFragment : Fragment() {
    private lateinit var tasarim: FragmentChangePasswordBinding
    private lateinit var viewModel: ChangePasswordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_change_password, container, false)
        tasarim.changePasswordFragment = this
        tasarim.toolbarTitle = "Parola Güncelle"

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarChangePassword)

        tasarim.toolbarChangePassword.setNavigationOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ChangePasswordViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun changePassword(current_password:String, new_password:String){
        if(current_password.isNotEmpty() && new_password.isNotEmpty()) {
            viewModel.changePassword(current_password, new_password, tasarim)
        } else {
            tasarim.currentPassword.setError("Zorunlu Alan")
            tasarim.newPassword.setError("Zorunlu Alan")
        }
    }

}