package com.example.stmcourt.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.stmcourt.R
import com.example.stmcourt.StmCourt
import com.example.stmcourt.model.response.home.Data
import com.example.stmcourt.model.response.home.HomeRequest
import com.example.stmcourt.model.response.login.User
import com.example.stmcourt.utils.Helpers.formatPrice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*
import kotlin.math.log


class PaymentFragment : Fragment(), PaymentContract.View {

    var total: Int  = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<Data>("loginResponse")
        initView(data)


    }

    private fun initView(data: Data?) {
        tvTitle.text = data?.name
        tvPrice.formatPrice(data?.price.toString())

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvNameItem.text = data?.name
        tvHarga.formatPrice(data?.price.toString())
        tvTotal.formatPrice(data?.price.toString())

        var user = StmCourt.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)
        Log.d("response", userResponse.toString())
        tvName.text = userResponse?.name
        tvPhone.text = userResponse?.phoneNumber
        tvClass.text = userResponse?.kelas

        btn_checkout.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionFragmentSuccess)
        }
    }

    override fun onCheckoutSuccess(homeRequest: HomeRequest) {
        TODO("Not yet implemented")
    }

    override fun onCheckoutFailed(message: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }


}