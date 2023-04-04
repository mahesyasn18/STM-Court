package com.example.stmcourt

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.example.stmcourt.network.HttpClient

class StmCourt : MultiDexApplication(){
    companion object {
        lateinit var instance: StmCourt

        fun getApp(): StmCourt{
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences() : SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(this)
    }
    fun setToken(token:String){
        getPreferences().edit().putString("PREFERENCES_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }
    fun  getToken():String?{
        return getPreferences().getString("PREFERENCES_TOKEN", null)
    }
    fun setUser(user:String){
        getPreferences().edit().putString("PREFERENCES_USER", user).apply()

    }
    fun getUser():String?{
        return getPreferences().getString("PREFERENCES_USER", null)
    }
}