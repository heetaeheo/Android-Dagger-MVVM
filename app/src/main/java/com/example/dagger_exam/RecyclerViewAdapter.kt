package com.example.dagger_exam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dagger_exam.databinding.RecyclerViewListBinding
import com.example.dagger_exam.model.RecyclerData

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var listData: List<RecyclerData>? = null

    class MyViewHolder(private val binding: RecyclerViewListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RecyclerData) = with(binding) {
            textName.text = data.name
            textDescription.text = data.description

            Glide.with(imageView)
                .load(data.owner?.avatar_url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RecyclerViewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if (listData == null) 0
        else listData?.size!!
    }
}