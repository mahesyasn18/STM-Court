package com.example.stmcourt.ui.home

import com.example.stmcourt.base.BasePresenter
import com.example.stmcourt.base.BaseView
import com.example.stmcourt.model.response.login.LoginResponse

interface HomeContract {

    interface View: BaseView{
        fun onHomeSuccess(loginResponse: LoginResponse)
        fun onHomeFailed(message:String)
    }

    interface Presenter: HomeContract, BasePresenter{
        fun getHome()
    }
}