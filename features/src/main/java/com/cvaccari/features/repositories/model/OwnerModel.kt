package com.cvaccari.features.repositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OwnerModel(
    @SerializedName("avatar_url") val image: String,
    @SerializedName("login") val login: String
): Serializable
