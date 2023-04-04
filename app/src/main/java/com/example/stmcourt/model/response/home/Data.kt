package com.example.stmcourt.model.response.home


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val created_at: String?,
    @Expose
    @SerializedName("deleted_at")
    val deleted_at: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String?,
    @Expose
    @SerializedName("price")
    val price: Int?,
    @Expose
    @SerializedName("rate")
    val rate: Int?,
    @Expose
    @SerializedName("types")
    val types: String?,
    @Expose
    @SerializedName("updated_at")
    val updated_at: String?
):Parcelable