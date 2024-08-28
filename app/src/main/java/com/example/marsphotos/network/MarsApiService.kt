package com.example.marsphotos.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
// 1. line  Create an retrofit object
// 2. line add instance of converter factory Scalars JSON -> String

@ExperimentalSerializationApi
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// Define How retrofit Talks with the web servers using HTTPs
// Annotation tells retrofit this is GET Request at END POINT Photos
// when getPhotos() invoked retrofit append "photos" to the BASE_URL
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>

}
// singleTon object that rest of application will use for Retrofit Services
object MarsApi{
    @OptIn(ExperimentalSerializationApi::class)
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
// Each call to MarsApi.retrofitServices, caller access same singleton retrofit object that implement interface
