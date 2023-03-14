package com.tarasov_denis.pagingtraining

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tarasov_denis.pagingtraining.data.ListCharacters
import com.tarasov_denis.pagingtraining.data.Character
import com.tarasov_denis.pagingtraining.network.RetrofitClient
import kotlinx.coroutines.flow.Flow


class RepoListViewModel() : ViewModel() {
    private var repository: RepoListRepository = RepoListRepository(RetrofitClient.getNetworkApi2())
    private var currentUserName: String? = null
    private var currentSearchResult: Flow<PagingData<Character>>? = null

    fun searchRepos(): Flow<PagingData<Character>> {
        Log.d("Denis", "searchRepos запустилось")
      //  val lastResult = currentSearchResult
      //  if (lastResult ==  null) {
       //     return lastResult

      //  currentUserName = username
        val newResult = repository.getCharactersPage()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
       // Log.d("Denis", newResult.toString())
        return newResult
    }
}