package com.example.ecommerceapp.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.common.viewBinding
import com.example.ecommerceapp.databinding.FragmentDetailBinding
import com.example.ecommerceapp.viewmodel.shared.SharedViewModel
import com.example.ecommerceapp.viewmodel.ui.DetailsViewModel


class DetailsFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args : DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {
            binding.tvCategory.text = it.product.category
            binding.tvCurrentPrice.text = "${it.product.price}$"
            Glide.with(binding.imgProduct).load(it.product.image).into(binding.imgProduct)
            binding.tvDescription.text = it.product.description
            binding.tvProductTitle.text = it.product.title

            binding.addToBag.setOnClickListener {btn->
                sharedViewModel.addProduct(it.product)
                Toast.makeText(context,"${it.product.title}", Toast.LENGTH_SHORT).show()
            }

            binding.imgAddToFavorite.setOnClickListener { _ ->
                viewModel.addProduct(it.product)
                binding.imgAddToFavorite.setColorFilter(ContextCompat.getColor(requireContext(),R.color.red))
            }
        }
    }

}