package com.cvaccari.features.repositories.model

import com.google.gson.annotations.SerializedName

class OwnerModel(
    @SerializedName("avatar_url") val image: String,
    @SerializedName("login") val login: String
)
