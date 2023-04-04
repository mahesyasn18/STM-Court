package com.example.stmcourt.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stmcourt.R

import com.example.stmcourt.model.dummy.HomeVerticalModel
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new_taste.*


class HomeNewTasteFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {
    private var foodlist:ArrayList<HomeVerticalModel> = ArrayList()
    private var newTasteList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")

        var adapter = HomeNewTasteAdapter(newTasteList!!,this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }


    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

}