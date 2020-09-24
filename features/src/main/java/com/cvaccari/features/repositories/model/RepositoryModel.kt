package com.cvaccari.features.repositories.model

import com.google.gson.annotations.SerializedName

class RepositoryModel(
    @SerializedName("name") val name: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("owner") val owner: OwnerModel
)

