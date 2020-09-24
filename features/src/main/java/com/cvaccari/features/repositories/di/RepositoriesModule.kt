package com.cvaccari.features.repositories.di

import com.cvaccari.features.repositories.RepositoriesApi
import com.cvaccari.features.repositories.RepositoriesContract
import com.cvaccari.features.repositories.RepositoriesPresenter
import com.cvaccari.features.repositories.RepositoriesRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit


val repositoriesModule = Kodein.Module("repositoriesModule") {
    bind<RepositoriesContract.Presenter>() with factory { view: RepositoriesContract.View ->
        RepositoriesPresenter(
            view,
            instance()
        )
    }

    bind<RepositoriesContract.Repository>() with provider {
        RepositoriesRepository(
            instance()
        )
    }

    bind<RepositoriesApi>() with provider {
        instance<Retrofit>().create(RepositoriesApi::class.java)
    }

}