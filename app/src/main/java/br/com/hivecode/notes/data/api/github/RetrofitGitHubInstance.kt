package br.com.hivecode.notes.data.api.github

import br.com.hivecode.notes.BuildConfig
import br.com.hivecode.notes.data.entity.Repo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

object RetrofitGitHubInstance{

    private const val ONE_MINUTE: Long = 60

    private val client = OkHttpClient.Builder()
        .readTimeout(ONE_MINUTE, TimeUnit.SECONDS)
        .connectTimeout(ONE_MINUTE, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_GITHUB_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    val api = retrofit.create(RepoAPI::class.java)

}

interface RepoAPI {
    @GET("users/{user_id}/repos")
    fun getRepos(@Path("user_id") user : String): Deferred<MutableList<Repo>>
}