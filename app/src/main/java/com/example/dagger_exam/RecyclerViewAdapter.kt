package com.example.dagger_exam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dagger_exam.databinding.RecyclerViewListBinding
import com.example.dagger_exam.model.RecyclerData

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData : List<RecyclerData>? = null


    fun setupdatedData(listData:List<RecyclerData>){
        this.listData = listData
    }

    class MyViewHolder(private val binding:RecyclerViewListBinding): RecyclerView.ViewHolder(binding.root){

        val imageView = binding.ImageView
        val textName = binding.textName
        val textdescription =binding.textdescription

        fun bind(data : RecyclerData){
            textName.setText(data.name)
            textdescription.setText(data.description)

            Glide.with(imageView)
                .load(data.owner?.avatar_url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val binding = RecyclerViewListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null )return 0
        else return listData?.size!!
    }
}