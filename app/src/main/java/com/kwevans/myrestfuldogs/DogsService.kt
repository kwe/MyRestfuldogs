package com.kwevans.myrestfuldogs

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DogsService {
    @GET("dogs")
    suspend fun getDogs(): Response<Dogs>

    @POST("dogs")
    suspend fun uploadDog(@Body dog:Dog): Response<Dog>

}