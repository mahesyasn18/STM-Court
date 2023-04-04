package com.example.stmcourt.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stmcourt.R
import com.example.stmcourt.StmCourt
import com.example.stmcourt.model.dummy.HomeModel
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.model.response.home.HomeRequest
import com.example.stmcourt.model.response.login.User
import com.example.stmcourt.ui.detail.DetailActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback ,HomeContract.View  {

    private var newStateList : ArrayList<Data> = ArrayList()
    private var popularList : ArrayList<Data> = ArrayList()
    private var recomendedList : ArrayList<Data> = ArrayList()
    private lateinit var presenter:HomePresenter
    var progressDialog: Dialog?=null


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
      val root = inflater.inflate(R.layout.fragment_home, container, false)
      return root
  }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        presenter = HomePresenter(this)
        presenter.getHome()

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onHomeSuccess(homeRequest: HomeRequest) {

        for (a in homeRequest.data.indices) {

            var items:List<String> = homeRequest.data[a].types?.split(",") ?: ArrayList()
            for (x in items.indices) {
                if (items[x].equals("new_food", true)) {
                    newStateList?.add(homeRequest.data[a])
                } else if (items[x].equals("recommended", true)) {
                    recomendedList?.add(homeRequest.data[a])
                } else if (items[x].equals("popular", true)) {
                    popularList?.add(homeRequest.data[a])
                }
            }

        }

        var adapter = HomeAdapter(homeRequest.data, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newStateList, popularList, recomendedList)
        viewPager.adapter = sectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message , Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onClick(v: View, data: Data) {
      val details =  Intent(activity, DetailActivity::class.java)
        startActivity(details)
    }


}