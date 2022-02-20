package com.cetinmustafa.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cetinmustafa.foodapp.databinding.BasketCardTasarimBinding
import com.cetinmustafa.foodapp.entity.BasketFoods
import com.cetinmustafa.foodapp.viewmodel.BasketFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class BasketAdapter(
    var mContext: Context,
    var foodList: List<BasketFoods>,
    var viewModel: BasketFragmentViewModel,
) : RecyclerView.Adapter<BasketAdapter.CardDesignHolder>(){
    private lateinit var auth: FirebaseAuth
    inner class CardDesignHolder(cardDesignBinding: BasketCardTasarimBinding) :
            RecyclerView.ViewHolder(cardDesignBinding.root){
                var cardDesignBinding: BasketCardTasarimBinding

                init {
                    this.cardDesignBinding = cardDesignBinding
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = BasketCardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(tasarim)

    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val food = foodList.get(position)
        val t = holder.cardDesignBinding
        t.foodObject = food

        auth = FirebaseAuth.getInstance()

        val imageName = foodList.get(position).yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"

        Picasso.get()
            .load(url)
            .into(t.imageViewBasketFoodImage)

        t.imageViewDeleteFood.setOnClickListener {
            Snackbar.make(it, "${foodList.get(position).yemek_adi} sepetinizden silinsin mi ?", Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    viewModel.deleteFood(foodList.get(position).sepet_yemek_id, auth.currentUser!!.email!!)
                }.show()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}