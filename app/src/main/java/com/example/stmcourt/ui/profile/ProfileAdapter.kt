package com.example.stmcourt.ui.profile

import   android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stmcourt.R
import com.example.stmcourt.model.dummy.ProfileModel
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileAdapter (private val listData: List<ProfileModel>,
                      private val itemAdapterCallback: ItemAdapterCallback,): RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return  listData.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: ProfileModel, itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                caption.text = data.title
//           Glide.with(context).load(loginResponse.src).into(idPoster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data) }
            }
        }
    }

    interface  ItemAdapterCallback{
        fun onClick(v: View, data:ProfileModel)
    }


}