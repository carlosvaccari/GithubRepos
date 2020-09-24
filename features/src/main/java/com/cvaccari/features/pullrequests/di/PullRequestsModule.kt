package com.cvaccari.features.pullrequests.di

import com.cvaccari.features.pullrequests.PullRequestsApi
import com.cvaccari.features.pullrequests.PullRequestsContract
import com.cvaccari.features.pullrequests.PullRequestsPresenter
import com.cvaccari.features.pullrequests.PullRequestsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.Retrofit


val pullRequestsModule = Kodein.Module("pullRequestsModule") {
    bind<PullRequestsContract.Presenter>() with factory { view: PullRequestsContract.View ->
        PullRequestsPresenter(
            view,
            instance()
        )
    }

    bind<PullRequestsContract.Repository>() with provider {
        PullRequestsRepository(
            instance()
        )
    }

    bind<PullRequestsApi>() with provider {
        instance<Retrofit>().create(PullRequestsApi::class.java)
    }

}