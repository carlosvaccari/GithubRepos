package com.cvaccari.features.repositories

import com.cvaccari.features.repositories.model.OwnerModel
import com.cvaccari.features.repositories.model.RepositoriesModel
import com.cvaccari.features.repositories.model.RepositoryModel

object RepositoriesRepositoryHelper {

    val successfulRepositoriesRequest = RepositoriesModel(
        totalCount = 1,
        incompleteResults = false,
        items = listOf(
            RepositoryModel(
                "",
                0,
                0,
                "",
                OwnerModel("", "")
            )
        )
    )

    val currentPage = 1

    val error = Exception()
}