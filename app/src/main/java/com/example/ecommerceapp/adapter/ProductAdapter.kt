package com.example.ecommerceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.model.product.ProductItem

class ProductAdapter : ListAdapter<ProductItem, ProductAdapter.ProductHolder>(DiffCallback) {

    var onProductClick:(ProductItem) -> Unit = {}
    var addBasketButton:(ProductItem) -> Unit = {}


    object DiffCallback : DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }

    class ProductHolder(val binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item = currentList[position]
        holder.binding.tvProductName.text = item.title
        holder.binding.tvProductPrice.text = "${item.price}$"
        Glide.with(holder.binding.imgProduct).load(item.image).into(holder.binding.imgProduct)

        holder.binding.root.setOnClickListener {
            currentList.getOrNull(position)?.let{
                onProductClick(currentList[position])
            }

        }

        holder.binding.addToBag.setOnClickListener {
            currentList.getOrNull(position)?.let {product->
                addBasketButton(product)
                Toast.makeText(it.context,"${product.title}",Toast.LENGTH_SHORT).show()
            }

        }

    }
}