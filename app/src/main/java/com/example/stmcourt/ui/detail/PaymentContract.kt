package com.example.stmcourt.ui.detail

import com.example.stmcourt.base.BasePresenter
import com.example.stmcourt.base.BaseView
import com.example.stmcourt.model.response.home.HomeRequest

interface PaymentContract {

    interface View: BaseView{
        fun onCheckoutSuccess(homeRequest: HomeRequest)
        fun onCheckoutFailed(message:String)
    }

    interface Presenter: PaymentContract, BasePresenter{
        fun getCheckout()
    }
}