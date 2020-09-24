package com.cvaccari.features.repositories.model

import com.google.gson.annotations.SerializedName

class RepositoriesModel(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<RepositoryModel>
)