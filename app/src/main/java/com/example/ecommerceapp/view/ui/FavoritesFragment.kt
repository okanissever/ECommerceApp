package com.example.ecommerceapp.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.FavoritesAdapter
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentFavoriteBinding
import com.example.ecommerceapp.viewmodel.ui.FavoritesViewModel


class FavoritesFragment : Fragment(R.layout.fragment_favorite) {
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter = FavoritesAdapter(viewModel)
        binding.rvFavorites.adapter = favoritesAdapter

        viewModel.getAll()
        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.productList.observe(viewLifecycleOwner){product->
            product?.let {
                favoritesAdapter.submitList(it)
                binding.rvFavorites.adapter = favoritesAdapter
            }
        }
    }
}