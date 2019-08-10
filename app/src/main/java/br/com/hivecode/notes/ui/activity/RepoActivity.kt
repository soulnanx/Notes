package br.com.hivecode.notes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.hivecode.notes.R
import br.com.hivecode.notes.databinding.ActivityReposBinding
import br.com.hivecode.notes.ui.adapter.ReposAdapter
import br.com.hivecode.notes.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoActivity : AppCompatActivity() {

    private val reposViewModel : RepoViewModel by viewModel()

    private val binding : ActivityReposBinding by lazy {
        DataBindingUtil.setContentView(this@RepoActivity, R.layout.activity_repos) as ActivityReposBinding
    }

    private val reposAdapter : ReposAdapter by lazy {
        ReposAdapter(reposViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)

        binding.repoViewModel = reposViewModel
        setObservers()
        setAdapter()
    }

    private fun setAdapter() {
        binding.reposRv.adapter = reposAdapter
    }

    private fun setEvents() {
        binding.searchBtn.setOnClickListener {
            reposViewModel.loadRepos()
        }
    }

    private fun setObservers() {
        reposViewModel.reposList.observe(this, Observer {
            reposAdapter.add(it)
        })
    }
}