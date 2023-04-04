package com.example.stmcourt.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.stmcourt.R
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.utils.Helpers.formatPrice

import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    var bundle:Bundle? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
        DetailFragmentArgs.fromBundle(it).data?.let{
            initView(it)
        }

        }
       btnOrderNow.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.actionfragmentPayment, bundle)

       }
    }

    private fun initView(data: Data?) {
        bundle = bundleOf("loginResponse" to data)
       data?.let {
           Glide.with(requireContext())
               .load(it.picturePath)
               .into(ivPoster)
           tvTitle.text = it.name
           TVdeskripsi.text = it.description
           tvingredient.text = it.ingredients

           tvTotal.formatPrice(data?.price.toString())
       }
    }


}