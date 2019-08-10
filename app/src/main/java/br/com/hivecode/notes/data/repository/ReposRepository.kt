package br.com.hivecode.notes.data.repository

import androidx.lifecycle.MutableLiveData
import br.com.hivecode.notes.data.api.github.RepoAPI
import br.com.hivecode.notes.data.api.github.RetrofitGitHubInstance
import br.com.hivecode.notes.data.entity.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class ReposRepository(private val retrofit: RepoAPI) {

    val reposLiveData = MutableLiveData<MutableList<Repo>>()

    fun fetchRepos(username : String = "soulnanx") : MutableLiveData<MutableList<Repo>> {

        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

        scope.launch {
            try {
                val reposList = retrofit.getRepos(username).await()
                reposLiveData.postValue(reposList)
            } catch (e : Exception){
                e.toString()
            }
        }

        return reposLiveData
    }

}
