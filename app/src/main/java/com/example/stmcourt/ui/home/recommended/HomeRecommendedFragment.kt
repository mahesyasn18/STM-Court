package com.example.stmcourt.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stmcourt.R
import com.example.stmcourt.model.dummy.HomeVerticalModel
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.ui.detail.DetailActivity
import com.example.stmcourt.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeRecommendedFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {
    private var recommendedList:ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_recommended, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recommendedList = arguments?.getParcelableArrayList("loginResponse")

        var adapter = HomeNewTasteAdapter(recommendedList!!,this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }


    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

}