package com.example.stmcourt.ui.auth.register

import android.net.Uri
import android.view.View
import com.example.stmcourt.model.request.RegisterRequest
import com.example.stmcourt.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignupPresenter(private val view:SignupContract.View):SignupContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParms:View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            registerRequest.name,
            registerRequest.kelas,
            registerRequest.phoneNumber,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterSuccess(it1, viewParms) }
                } else {
                   view.onRegisterFailed(it.meta?.message.toString())
                }
            },
            {
                view.dismissLoading()
                view.onRegisterFailed(it.message.toString())
            })
        mCompositeDisposable!!.add(disposable)
    }

    override fun submitPhotoRegister(filePath: Uri, viewParms: View) {
        view.showLoading()

        var profileImageFile = File(filePath.path)
        val profileImageRequestBody = RequestBody.create("multipart/form-loginResponse".toMediaTypeOrNull(), profileImageFile)
        val profileImageParms = MultipartBody.Part.createFormData("file", profileImageFile.name,profileImageRequestBody )

        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(profileImageParms)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.dismissLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterPhotoSuccess(viewParms) }
                } else {
                     view.onRegisterFailed(it.meta?.message.toString())
                }
            },
                {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                })
        mCompositeDisposable!!.add(disposable)
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }


}