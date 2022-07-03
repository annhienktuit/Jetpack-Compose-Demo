package com.annhienktuit.compose_demo.models

import com.google.gson.annotations.SerializedName

/**
 *Created by Nhien Nguyen on 7/3/2022
 */
data class CountryInfo(
    @SerializedName("country")
    val name: String,
    @SerializedName("city")
    val capital: String
)
