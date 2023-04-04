package com.example.stmcourt.ui.auth.register

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.stmcourt.R
import com.example.stmcourt.StmCourt
import com.example.stmcourt.model.request.RegisterRequest
import com.example.stmcourt.model.response.Wrapper
import com.example.stmcourt.model.response.login.LoginResponse
import com.example.stmcourt.ui.auth.AuthNavActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_data.*


class SignupDataFragment : Fragment(), SignupContract.View {

    private lateinit var data:RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog:Dialog?=null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_signup_data, container, false)
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("loginResponse")!!

        initListener()
        initView()

    }

    private fun initListener(){

        btn_signup.setOnClickListener {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var password_confirmation = etPasswordConfirmation.text.toString()

            data.let {
                it.email = email
                it.password = password
                it.password_confirmation = password_confirmation
            }

            if (email.isNullOrEmpty()){
                etEmail.error= "Enter your Email Address"
                etEmail.requestFocus()
            }else if (password.isNullOrEmpty()){
                etPassword.error= "Enter your Password"
                etPassword.requestFocus()
            }else if (password_confirmation.isNullOrEmpty()){
                etPasswordConfirmation.error= "Enter your Password Confirmation"
                etPasswordConfirmation.requestFocus()
            }else{
                presenter.submitRegister(data, it)
            }

        }

    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        StmCourt.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        StmCourt.getApp().setUser(json)

        if (data.filePath == null){
            Navigation.findNavController(view)
                .navigate(R.id.actionSuccess, null)
            (activity as AuthNavActivity).toolbarSignupSuccess()
        }else{
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.actionSuccess, null)
        (activity as AuthNavActivity).toolbarSignupSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }


}