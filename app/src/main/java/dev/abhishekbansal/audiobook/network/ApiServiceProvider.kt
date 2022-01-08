package dev.abhishekbansal.audiobook.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiServiceProvider {

    fun provide(
        baseUrl: String,
        moshi: Moshi,
        httpClient: OkHttpClient
    ): ApiService {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        return builder.build().create(ApiService::class.java)
    }
}