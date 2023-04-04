package com.example.stmcourt.ui.auth.login

import com.example.stmcourt.base.BasePresenter
import com.example.stmcourt.base.BaseView
import com.example.stmcourt.model.response.login.LoginResponse

interface SigninContract {

    interface View: BaseView{
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message:String)
    }

    interface Presenter: SigninContract, BasePresenter{
        fun submitLogin(email:String, password:String)
    }
}