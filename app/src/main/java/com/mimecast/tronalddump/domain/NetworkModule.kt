package com.mimecast.tronalddump.domain

import com.mimecast.tronalddump.BuildConfig
import com.mimecast.tronalddump.base.AppDefine
import com.mimecast.tronalddump.data.api.common.header.Headers
import com.mimecast.tronalddump.data.api.common.header.NetworkFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 *
 * This is Singleton. Ideally we shouldn't have the Context in this class but
 * if we need to have it we should make the class singleton using the SingletonHolder
 *
 * Read here for more info https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 *
 */
object NetworkModule {

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(AppDefine.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        // Class for logging network calls
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        this.addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header(Headers.ACCEPT, NetworkFactory.accept)
                .build()
            chain.proceed(request)

        }.addInterceptor(interceptor)

    }.build()

}