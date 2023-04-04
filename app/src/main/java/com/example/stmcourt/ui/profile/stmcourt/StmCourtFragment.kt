package com.example.stmcourt.ui.profile.stmcourt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stmcourt.R
import com.example.stmcourt.model.dummy.ProfileModel
import com.example.stmcourt.ui.profile.ProfileAdapter
import kotlinx.android.synthetic.main.fragment_account.*

class StmCourtFragment : Fragment(),  ProfileAdapter.ItemAdapterCallback{
    private var menuArrayList: ArrayList<ProfileModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataDummy()
        var adapter = ProfileAdapter(menuArrayList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileModel("Help Center"))
        menuArrayList.add(ProfileModel("Privacy & Policy"))
        menuArrayList.add(ProfileModel("Terms & Conditions"))
    }

    override fun onClick(v: View, data: ProfileModel) {
        Toast.makeText(context,"ini menu yang kamu klik"+data.title, Toast.LENGTH_SHORT).show()
    }

}