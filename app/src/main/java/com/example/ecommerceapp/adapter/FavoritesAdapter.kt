package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemFavoriteBinding
import com.example.ecommerceapp.model.product.ProductItem
import com.example.ecommerceapp.viewmodel.ui.FavoritesViewModel

class FavoritesAdapter(private val viewModel : FavoritesViewModel) : androidx.recyclerview.widget.ListAdapter<ProductItem, FavoritesAdapter.FavoritesHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }

    class FavoritesHolder(val binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        val item = currentList[position]
        holder.binding.apply {
            tvPrice.text = "${item.price}$"
            tvTitle.text = item.title
            Glide.with(imgProduct).load(item.image).into(imgProduct)
        }

     holder.binding.imgDelete.setOnClickListener {
         if(position < currentList.size){
             viewModel.deleteProduct(currentList[position])
         }

     }
    }
}