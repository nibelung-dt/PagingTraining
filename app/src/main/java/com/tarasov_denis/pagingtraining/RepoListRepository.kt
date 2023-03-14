package com.tarasov_denis.pagingtraining

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tarasov_denis.pagingtraining.network.NetworkApi

class RepoListRepository(private val api: NetworkApi) {
    /*
    fun fetchRepos(userName: String) = Pager(
        pagingSourceFactory = { GithubPagingSource(api, userName) },
        config = PagingConfig(pageSize = 20)
    ).flow

     */

    fun getCharactersPage() = Pager(
        pagingSourceFactory = { GithubPagingSource(api) },
        config = PagingConfig(pageSize = 20)
    ).flow
}