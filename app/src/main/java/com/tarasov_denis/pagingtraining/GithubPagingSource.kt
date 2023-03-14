package com.tarasov_denis.pagingtraining

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tarasov_denis.pagingtraining.data.ListCharacters
import com.tarasov_denis.pagingtraining.data.Character
import com.tarasov_denis.pagingtraining.network.NetworkApi

private const val FIRST_PAGE = 1

class GithubPagingSource(
    private val api: NetworkApi
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    // getNetworkApi2
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            Log.d("Denis", "load запустилось")
            val page = params.key ?: FIRST_PAGE
            val response = api.getCharactersPage(page)  // не выполняется запрос в сеть
          // val response = listCharacters1
            Log.d("response", response.results.size.toString())
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    val rick = Character(1, "Rick", "aa", "dd", "fff", "dgf",
        Character.Origin("ddd", "ddss"), Character.Location("sss", "dss"),
        "dgfds", listOf("dd"), "hgh")
    val sampleList = listOf(rick)
    val listCharacters1 = ListCharacters(ListCharacters.Info(), sampleList)
}