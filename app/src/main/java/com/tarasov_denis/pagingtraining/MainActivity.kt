package com.tarasov_denis.pagingtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val REPO_NAME = "google"

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var searchJob: Job? = null
    private var repoListAdapter: RepoListAdapter? = null
    private var viewModel: RepoListViewModel? = null// by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_listing)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(RepoListViewModel::class.java)
        repoListAdapter = RepoListAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView?.adapter = repoListAdapter?.withLoadStateHeaderAndFooter(
            header = RepoLoadStateAdapter(retry = { search() }),
            footer = RepoLoadStateAdapter(retry = { search() })
        )
        recyclerView?.layoutManager = LinearLayoutManager(this)

        search()

    }

    private fun search() {
        Log.d("Denis", "search запустилось")
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel?.searchRepos()?.collect {
                Log.d("Denis", "job в search запустилось")
                repoListAdapter?.submitData(it)
            }
        }
    }
}