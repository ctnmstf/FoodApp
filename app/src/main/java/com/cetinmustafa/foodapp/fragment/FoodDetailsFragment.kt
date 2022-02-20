package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.databinding.FragmentFoodDetailsBinding
import com.cetinmustafa.foodapp.viewmodel.FoodDetailsFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso


class FoodDetailsFragment : Fragment() {
    private lateinit var tasarim:FragmentFoodDetailsBinding
    private lateinit var viewModel:FoodDetailsFragmentViewModel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_food_details, container, false)
        tasarim.foodDetailsFragment = this

        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbar2)

        tasarim.toolbar2.setNavigationOnClickListener(View.OnClickListener {
            activity!!.onBackPressed()
        })

        val bundle:FoodDetailsFragmentArgs by navArgs()
        val food = bundle.food

        auth = FirebaseAuth.getInstance()
        tasarim.email = auth.currentUser!!.email!!

        tasarim.foodObject = food
        tasarim.foodDetailToolbarTitle = food.yemek_adi

        val imageName = food.yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"

        Picasso.get()
            .load(url)
            .into(tasarim.imageViewFoodDetailImage)

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodDetailsFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    fun addToBasket(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:String, kullanici_adi:String){
        viewModel.addToBasket(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet.toInt(), kullanici_adi, tasarim)
    }

    fun increaseBasketCount(){
        val counter = tasarim.textViewBasketCount.text.toString().toInt()
        tasarim.textViewBasketCount.text = (counter + 1).toString()
    }

    fun decreaseBasketCount(){
        val counter = tasarim.textViewBasketCount.text.toString().toInt()
        if(counter > 1){
            tasarim.textViewBasketCount.text = (counter - 1).toString()
        }
    }
}