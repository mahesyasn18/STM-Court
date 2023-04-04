package com.example.stmcourt.ui.auth.register


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stmcourt.R
import com.example.stmcourt.model.request.RegisterRequest
import com.example.stmcourt.ui.auth.AuthNavActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignupFragment : Fragment() {

    var filePath:Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListener()


    }

    private fun initListener(){
        ivPhotoProfile.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        btn_continue.setOnClickListener {
            var name = etName.text.toString()
            var kelas = etKelas.text.toString()
            var phoneNumber = etPhoneNumber.text.toString()

            if (name.isNullOrEmpty()){
                etName.error = "Enter Your Full Name"
                etName.requestFocus()
            }else if (kelas.isNullOrEmpty()){
                etKelas.error = "Enter Your Class"
                etKelas.requestFocus()
            }else if (phoneNumber.isNullOrEmpty()){
                etPhoneNumber.error = "Enter Your Class"
                etPhoneNumber.requestFocus()
            }else{
                var data = RegisterRequest(
                    name,
                    kelas,
                    phoneNumber,
                    "", "", "",
                    filePath
                )

                var bundle = Bundle()
                bundle.putParcelable("loginResponse", data)


                Navigation.findNavController(it)
                    .navigate(R.id.actiontoFragmentsignupdetail, bundle)
                (activity as AuthNavActivity).toolbarSignUpDetail()
            }




        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            filePath = data?.data
            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPhotoProfile)
        } else if(resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Task Cancelled by User", Toast.LENGTH_SHORT).show()
        }
    }






}