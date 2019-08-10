package br.com.hivecode.notes.di

import br.com.hivecode.notes.data.api.github.RetrofitGitHubInstance
import br.com.hivecode.notes.data.repository.NotesRepository
import br.com.hivecode.notes.data.repository.ReposRepository
import br.com.hivecode.notes.viewmodel.RepoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ModulesDependency {
    val appModule = module {
        single { NotesRepository() }
        single { RetrofitGitHubInstance.api }
        single { ReposRepository( get() ) }
        viewModel {
//            NoteViewModel( get() )
            RepoViewModel( get() )
        }
    }
}