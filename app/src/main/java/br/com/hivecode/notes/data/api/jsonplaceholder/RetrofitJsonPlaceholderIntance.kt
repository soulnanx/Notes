package br.com.hivecode.notes.data.api.jsonplaceholder

import br.com.hivecode.notes.BuildConfig
import br.com.hivecode.notes.data.entity.Post
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object RetrofitJsonPlaceholderIntance{

    private const val ONE_MINUTE: Long = 60

    private val client = OkHttpClient.Builder()
        .readTimeout(ONE_MINUTE, TimeUnit.SECONDS)
        .connectTimeout(ONE_MINUTE, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_JSON_PLACEHOLDER_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    val api = retrofit.create(PostAPI::class.java)
}

interface PostAPI {
    @GET("post")
    fun getPosts(): Deferred<Post>
}