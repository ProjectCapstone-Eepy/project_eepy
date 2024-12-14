package com.example.eepyapp.data.response

import com.google.gson.annotations.SerializedName

// Data untuk request prediksi sleep quality
data class SleepQualityRequest(
    @field:SerializedName("gender")
    val gender: Int,

    @field:SerializedName("age")
    val age: Int,

    @field:SerializedName("physical_activity")
    val physicalActivity: Int,

    @field:SerializedName("stress_level")
    val stressLevel: Int,

    @field:SerializedName("sleep_duration")
    val sleepDuration: Int
)

// Data untuk response prediksi sleep quality
data class SleepQualityResponse(
    @field:SerializedName("prediction")
    val prediction: Float
)

// Data untuk request prediksi sleep duration
data class SleepDurationRequest(
    @field:SerializedName("gender")
    val gender: Int,

    @field:SerializedName("age")
    val age: Int,

    @field:SerializedName("physical_activity")
    val physicalActivity: Int,

    @field:SerializedName("stress_level")
    val stressLevel: Int
)

// Data untuk response prediksi sleep duration
data class SleepDurationResponse(
    @field:SerializedName("prediction")
    val prediction: Float
)
