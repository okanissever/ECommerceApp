package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemBagProductBinding
import com.example.ecommerceapp.model.product.ProductItem
import com.example.ecommerceapp.viewmodel.shared.SharedViewModel

class BagAdapter(val viewModel: SharedViewModel) : ListAdapter<ProductItem,BagAdapter.BagHolder>(DiffCallback){

    object DiffCallback : DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }

    }

    class BagHolder(val binding : ItemBagProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagHolder {
        val binding = ItemBagProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BagHolder(binding)
    }

    override fun onBindViewHolder(holder: BagHolder, position: Int) {
        val item = currentList[position]
        holder.binding.tvProductName.text = item.title.toString()
        holder.binding.tvPrice.text = "${item.price}$"
        Glide.with(holder.binding.imgProduct).load(item.image).into(holder.binding.imgProduct)

        holder.binding.imgDelete.setOnClickListener {
            if(position < currentList.size) {
                viewModel.removeProduct(currentList[position])
            }
        }

    }


}