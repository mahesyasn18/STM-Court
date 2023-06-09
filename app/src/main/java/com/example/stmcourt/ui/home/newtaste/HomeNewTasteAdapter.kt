package com.example.stmcourt.ui.home.newtaste

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stmcourt.R
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeNewTasteAdapter (private val listData: List<Data>,
                           private val itemAdapterCallback: ItemAdapterCallback,): RecyclerView.Adapter<HomeNewTasteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewTasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallback)
    }



    override fun getItemCount(): Int {
        return  listData.size
    }


    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                tvTitle.text = data.name
                tvSubtitle.formatPrice(data.price.toString())
                rbFood.rating = data.rate!!.toFloat() ?: 0f

                Glide.with(this).load(data.picturePath).into(imageView5);


                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data) }



            }
        }
    }


    interface  ItemAdapterCallback{
        fun onClick(v: View, data:Data)
    }


}