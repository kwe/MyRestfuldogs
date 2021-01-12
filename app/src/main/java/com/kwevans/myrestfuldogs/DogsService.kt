package com.kwevans.myrestfuldogs

import retrofit2.Response
import retrofit2.http.GET

interface DogsService {
    @GET("dogs")
    suspend fun getDogs(): Response<Dogs>

}