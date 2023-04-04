package com.example.stmcourt.utils

import android.widget.TextView
import com.google.gson.*
import kotlinx.android.synthetic.main.item_home_vertical.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers{

    fun TextView.formatPrice(value: String){
        this.text = getCurrencyIDR(java.lang.Double.parseDouble(value))
    }

    fun getCurrencyIDR (price:Double):String{
        val  format = DecimalFormat("#,###,###")
        return "Rp "+format.format(price).replace(",".toRegex(), ".")
    }

    fun getDefaultGson(): Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .registerTypeAdapter(Date::class.java, JsonDeserializer{ json, _, _, ->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date>{src, _, _, ->
                val format =  SimpleDateFormat("", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null){
                   JsonPrimitive(format.format(src))
                }else{
                    null
                }

            }).create()

    }
}