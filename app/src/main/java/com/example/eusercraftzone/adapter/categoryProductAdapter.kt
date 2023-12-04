package com.example.eusercraftzone.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.eUserCraftzone.Model.AddProductModel
import com.example.eusercraftzone.activity.ProductDetailsActivity
import com.example.eusercraftzone.databinding.ItemCategoryProductLayoutBinding

class categoryProductAdapter(val context: Context, val list: ArrayList<AddProductModel>)
    :RecyclerView.Adapter<categoryProductAdapter.categoryProductViewHolder>(){

    inner class categoryProductViewHolder(val binding: ItemCategoryProductLayoutBinding)

        : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryProductViewHolder {
        val binding = ItemCategoryProductLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return categoryProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: categoryProductViewHolder, position: Int) {
        Glide.with(context).load(list[position].productCoverImage).into(holder.binding.imageView3)

        holder.binding.textView4.text = list[position].productName
        holder.binding.textView5.text = list[position].productSp

        //when call product Detail this listener will call
        holder.itemView.setOnClickListener {
            val intent = Intent(context,ProductDetailsActivity::class.java)
            intent.putExtra("id",list[position].ProductId)
            context.startActivity(intent)
        }
    }
}