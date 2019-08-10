package br.com.hivecode.notes.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hivecode.notes.data.entity.Repo
import br.com.hivecode.notes.data.repository.ReposRepository

class RepoViewModel(private val repository : ReposRepository) : ViewModel() {

    var reposList = repository.reposLiveData

    fun loadRepos() {
        reposList = repository.fetchRepos()
    }

    fun turnToPrivate(repo: Repo){
//        repository.update(repo)
    }

}