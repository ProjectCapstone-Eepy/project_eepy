package com.example.eepyapp.data.retrofit

import com.example.eepyapp.data.response.SleepDurationRequest
import com.example.eepyapp.data.response.SleepDurationResponse
import com.example.eepyapp.data.response.SleepQualityRequest
import com.example.eepyapp.data.response.SleepQualityResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // Endpoint untuk prediksi kualitas tidur
    @POST("/quality")
    fun predictSleepQuality(@Body request: SleepQualityRequest): Call<SleepQualityResponse>

    // Endpoint untuk prediksi durasi tidur
    @POST("/duration")
    fun predictSleepDuration(@Body request: SleepDurationRequest): Call<SleepDurationResponse>
}
