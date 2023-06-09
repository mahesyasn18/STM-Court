package com.example.stmcourt.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stmcourt.R
import com.example.stmcourt.model.dummy.HomeModel
import com.example.stmcourt.model.response.home.Data
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeAdapter (
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,): RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
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
                rbFood.rating = data.rate?.toFloat() ?: 0f
           Glide.with(context).load(data.picturePath).into(idPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data) }
            }
        }
    }

    interface  ItemAdapterCallback{
        fun onClick(v: View, data:Data)
    }


}