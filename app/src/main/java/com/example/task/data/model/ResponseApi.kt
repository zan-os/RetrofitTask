package com.example.task.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseApi(

    @field:SerializedName("ResponseApi")
    val responseApi: List<ResponseApiItem>
)

@Parcelize
data class ResponseApiItem(

    @field:SerializedName("finish_rent_at")
    val finishRentAt: String? = null,

    @field:SerializedName("start_rent_at")
    val startRentAt: String? = null,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("price")
    val price: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("status")
    val status: Boolean,

    @field:SerializedName("updatedAt")
    val updatedAt: String
) : Parcelable
