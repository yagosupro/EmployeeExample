package ru.cdek.employeeslist.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.cdek.employeeslist.data.network.response.EmployeesResponse

interface Api {

    @GET("testTask.json")
    fun fetchData(): Deferred<EmployeesResponse>

    companion object {
        operator fun invoke(): Api {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

}