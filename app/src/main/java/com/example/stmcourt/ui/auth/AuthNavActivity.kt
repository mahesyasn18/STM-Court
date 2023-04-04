package com.example.stmcourt.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.stmcourt.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthNavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_nav)

        val pageRequest = intent.getIntExtra("page_request",0)
        if (pageRequest==2){
            toolbarSignUp()
            val navOption = NavOptions.Builder()
                .setPopUpTo(R.id.FragmentSigin,true)
                .build()

            Navigation.findNavController(findViewById(R.id.authHostFragment))
                .navigate(R.id.action_signup,null,navOption)
        }
    }
    fun toolbarSignUp(){
        toolbar.title = "Sign Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000,null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignUpDetail(){
        toolbar.title = "Account"
        toolbar.subtitle = "Make Sure it's valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000,null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSignupSuccess(){
        toolbar.visibility = View.GONE
    }


}