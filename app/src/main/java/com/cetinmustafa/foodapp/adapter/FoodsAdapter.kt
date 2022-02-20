package com.cetinmustafa.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cetinmustafa.foodapp.databinding.CardTasarimBinding
import com.cetinmustafa.foodapp.entity.Foods
import com.cetinmustafa.foodapp.fragment.HomePageFragmentDirections
import com.cetinmustafa.foodapp.viewmodel.HomePageFragmentViewModel
import com.squareup.picasso.Picasso

class FoodsAdapter(
    var mContext: Context,
    var foodList: List<Foods>,
    var viewModel: HomePageFragmentViewModel
) : RecyclerView.Adapter<FoodsAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(cardDesignBinding: CardTasarimBinding) :
        RecyclerView.ViewHolder(cardDesignBinding.root) {
        var cardDesignBinding: CardTasarimBinding

        init {
            this.cardDesignBinding = cardDesignBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater, parent, false)
        return CardDesignHolder(tasarim)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val food = foodList.get(position)
        val t = holder.cardDesignBinding
        t.foodObject = food

        val imageName = foodList.get(position).yemek_resim_adi
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"

        Picasso.get()
            .load(url)
            .into(t.imageViewFood)

        t.foodCard.setOnClickListener {
            val route = HomePageFragmentDirections.toFoodDetailRoute(food)
            Navigation.findNavController(it).navigate(route)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

}