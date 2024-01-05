package com.example.consumeapi.service_api

import com.example.consumeapi.model.Kontak
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface KontakService {
    @Headers(
        "Accept: Application/json"
    )

    @GET("kontak")
    suspend fun getKontak(): List<Kontak>

    @GET("kontak{id")
    suspend fun getKontakById(@Query("id") id:Int) : Kontak

    @POST("kontak")
    suspend fun insertKontak(@Body kontak:Kontak)

    @PUT("kontak{id")
    suspend fun updateKontak(@Query("id") id: Int, @Body kontak:Kontak)

    @DELETE("kontak/{id")
    suspend fun deleteKontak(@Query("id") id:Int) : Response<Void>


}