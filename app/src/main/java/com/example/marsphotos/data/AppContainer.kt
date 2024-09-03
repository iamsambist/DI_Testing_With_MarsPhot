package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer{
    val marsPhotosRepository : MarsPhotosRepository
}
class DefaultAppContainer : AppContainer{
    private  val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"
// 1. line  Create an retrofit object
// 2. line add instance of converter factory Scalars JSON -> String

    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    // Define How retrofit Talks with the web servers using HTTPs
// Annotation tells retrofit this is GET Request at END POINT Photos
// when getPhotos() invoked retrofit append "photos" to the BASE_URL

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }

}