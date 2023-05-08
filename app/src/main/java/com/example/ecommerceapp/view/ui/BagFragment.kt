package com.example.ecommerceapp.view.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapter.BagAdapter
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentBagBinding
import com.example.ecommerceapp.viewmodel.shared.SharedViewModel


class BagFragment : Fragment(R.layout.fragment_bag) {
    private val binding by viewBinding(FragmentBagBinding::bind)
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter: BagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BagAdapter(viewModel)
        binding.rvBagProducts.adapter = adapter
        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.selectedProducts.observe(viewLifecycleOwner){
            println("${it} verisi eklendi")
            adapter.submitList(it)
            binding.rvBagProducts.adapter = adapter
        }
        viewModel.price.observe(viewLifecycleOwner){price->
            binding.tvTotalAmount.text = String.format("%.2f$", price)
            binding.btnBuyNow.setOnClickListener {
                val action = BagFragmentDirections.actionBagFragmentToPaymentFragment(price.toFloat())
                Navigation.findNavController(it).navigate(action)
            }
        }

    }

}