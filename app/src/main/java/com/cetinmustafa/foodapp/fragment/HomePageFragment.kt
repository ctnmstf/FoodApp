package com.cetinmustafa.foodapp.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.cetinmustafa.foodapp.R
import com.cetinmustafa.foodapp.adapter.FoodsAdapter
import com.cetinmustafa.foodapp.databinding.FragmentHomePageBinding
import com.cetinmustafa.foodapp.viewmodel.HomePageFragmentViewModel
import androidx.recyclerview.widget.GridLayoutManager

class HomePageFragment : Fragment() {
    private lateinit var tasarim:FragmentHomePageBinding
    private lateinit var viewModel:HomePageFragmentViewModel
    private lateinit var adapter:FoodsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        tasarim.homePageFragment = this

        val layoutManager = GridLayoutManager(activity, 3)

        viewModel.foodList.observe(viewLifecycleOwner, {
            tasarim.layoutManager = layoutManager
            adapter = FoodsAdapter(requireContext(), it, viewModel)
            tasarim.foodsAdapter = adapter
        })

        val searchInput = tasarim.searchView

        searchInput.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }

        })

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: HomePageFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }
}
