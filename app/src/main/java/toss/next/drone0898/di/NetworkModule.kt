package toss.next.drone0898.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import toss.next.drone0898.BuildConfig
import toss.next.drone0898.data.remote.ApiService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl() = "https://toss.im/"


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val connectionTimeOut = (1000 * 30).toLong()
        val readTimeOut = (1000 * 5).toLong()

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            interceptor.redactHeader("Authorization");
            interceptor.redactHeader("Cookie");
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
            .connectTimeout(connectionTimeOut, TimeUnit.MILLISECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideKtorClient(okHttpClient: OkHttpClient): HttpClient {
        return HttpClient(OkHttp){
            engine {
                config{
                    followRedirects(true)
                }
                preconfigured = okHttpClient
            }
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostsService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}