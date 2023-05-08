package com.example.ecommerceapp.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.ProductAdapter
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.model.product.ProductItem
import com.example.ecommerceapp.viewmodel.shared.SharedViewModel
import com.example.ecommerceapp.viewmodel.ui.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter : ProductAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = ProductAdapter()
        observeLiveData()
        binding.recyclerView.adapter = adapter

        val tabTitles = arrayOf("All","Electronics","Jewelery","Men's clothing","Women's clothing")
        for (title in tabTitles){
            val tab = binding.tabLayout.newTab()
            tab.text = title
            binding.tabLayout.addTab(tab)
        }
        viewModel.getAllProduct()

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedCategory = tab?.text.toString()
                if(selectedCategory == "All"){
                    viewModel.getAllProduct()
                }
                if(selectedCategory == "Jewelery"){
                    viewModel.getJewelery()
                }
                if(selectedCategory == "Men's clothing"){
                    viewModel.getMensClothing()
                }
                if(selectedCategory == "Electronics"){
                    viewModel.getElectronics()
                }
                if(selectedCategory == "Women's clothing"){
                    viewModel.getWomensClothing()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                viewModel.getAllProduct()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val selectedCategory = tab?.text.toString()
                if(selectedCategory == "All"){
                    viewModel.getAllProduct()
                }
                if(selectedCategory == "Jewelery"){
                    viewModel.getJewelery()
                }
                if(selectedCategory == "Men's clothing"){
                    viewModel.getMensClothing()
                }
                if(selectedCategory == "Electronics"){
                    viewModel.getElectronics()
                }
                if(selectedCategory == "Women's clothing"){
                    viewModel.getWomensClothing()
                }
            }

        })

        adapter.addBasketButton = {product->
            sharedViewModel.addProduct(product)
            Log.d("HomeFragment", "Ürün eklenecek: $product")
        }
    }

    private fun observeLiveData(){
        viewModel.productList.observe(viewLifecycleOwner){product->
            product?.let {
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(it)
                binding.recyclerView.adapter = adapter
                adapter.onProductClick = {product->
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(product)
                    findNavController().navigate(action)
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner){error->
            error?.let {
                if(it){
                    binding.error.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                else{
                    binding.error.visibility = View.GONE
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){loading->
            loading?.let {
                if(it){
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                else
                {  binding.loading.visibility = View.GONE
                }
            }
        }

    }  }