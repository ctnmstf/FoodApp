package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.adapter.BasketAdapter
import com.cetinmustafa.foodapp.databinding.FragmentBasketBinding
import com.cetinmustafa.foodapp.viewmodel.BasketFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class BasketFragment : Fragment() {
    private lateinit var tasarim:FragmentBasketBinding
    private lateinit var viewModel:BasketFragmentViewModel
    private lateinit var adapter:BasketAdapter
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim =  DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        tasarim.basketFragment = this
        tasarim.basketToolbarTitle = "Sepet"

        viewModel.foodList.observe(viewLifecycleOwner, {
            adapter = BasketAdapter(requireContext(), it, viewModel)
            tasarim.basketAdapter = adapter
        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketFragmentViewModel by viewModels()
        this.viewModel = tempViewModel

        auth = FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadBasketFoods(auth.currentUser!!.email!!)
    }
}