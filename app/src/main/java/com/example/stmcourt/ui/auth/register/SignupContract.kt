package com.example.stmcourt.ui.auth.register

import android.net.Uri
import com.example.stmcourt.base.BasePresenter
import com.example.stmcourt.base.BaseView
import com.example.stmcourt.model.request.RegisterRequest
import com.example.stmcourt.model.response.login.LoginResponse

interface SignupContract {

    interface View: BaseView{
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter: SignupContract, BasePresenter{
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath:Uri, view: android.view.View)
    }
}