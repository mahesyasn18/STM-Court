package com.example.stmcourt.ui.auth.login
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.stmcourt.R
import com.example.stmcourt.StmCourt
import com.example.stmcourt.model.response.login.LoginResponse
import com.example.stmcourt.ui.MainActivity
import com.example.stmcourt.ui.auth.AuthNavActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*


class SigninFragment : Fragment(), SigninContract.View{

    lateinit var presenter: SigninPresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)

        if (!StmCourt.getApp().getToken().isNullOrEmpty()){
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initView()

        btn_signup.setOnClickListener {
            val signup = Intent(activity, AuthNavActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        btn_signin.setOnClickListener {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (email.isNullOrEmpty()){
                etEmail.error = "Please Enter Your Email Address"
                etEmail.requestFocus()
            }else if(password.isNullOrEmpty()){
                etPassword.error = "Please Enter Your Password"
                etPassword.requestFocus()
            }else{
                presenter.submitLogin(email, password)
            }

        }



    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {

        StmCourt.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        StmCourt.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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