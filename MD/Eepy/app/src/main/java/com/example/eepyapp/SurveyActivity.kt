package com.example.eepyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.eepyapp.data.retrofit.ApiConfig
import com.example.eepyapp.data.response.SleepDurationRequest
import com.example.eepyapp.data.response.SleepDurationResponse
import com.example.eepyapp.data.response.SleepQualityRequest
import com.example.eepyapp.data.response.SleepQualityResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SurveyActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private val surveyResponses = mutableMapOf<String, Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUpdating = intent.getBooleanExtra("isUpdating", false)

        if (!isUpdating && isSurveyDoneToday()) {
            navigateToHome()
            return
        }

        setContentView(R.layout.activity_survey)
        viewPager = findViewById(R.id.viewPager)

        val fragments = listOf(
            Q1Fragment { age ->
                saveResponse("age", age)
                navigateToNextPage()
            },
            Q2Fragment { gender ->
                saveResponse("gender", gender)
                navigateToNextPage()
            },
            Q4Fragment { physicalActivity ->
                saveResponse("physicalActivity", physicalActivity)
                navigateToNextPage()
            },
            Q5Fragment { stressLevel ->
                saveResponse("stressLevel", stressLevel)
                navigateToNextPage()
            },
            Q3Fragment { response ->
                saveResponse("sleepDuration", response.second)
                saveSleepData(response.first, response.second)
                sendDataToApi()
                saveSurveyDate()
                navigateToHome()
            }
        )

        viewPager.adapter = SurveyPagerAdapter(this, fragments)
    }

    private fun saveSleepData(date: String, sleep: Int) {
        val sharedPreferences = getSharedPreferences("EepyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val sleepDataString = sharedPreferences.getString("sleepData", "{}") ?: "{}"
        val sleepDataMap = Gson().fromJson(sleepDataString, HashMap::class.java) as HashMap<String, Int>

        sleepDataMap[date] = sleep
        editor.putString("sleepData", Gson().toJson(sleepDataMap))
        editor.apply()

        Log.d("SurveyActivity", "Sleep Data Updated: $sleepDataMap")
    }

    private fun saveInputToPreferences() {
        val sharedPreferences = getSharedPreferences("EepyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("lastGender", (surveyResponses["gender"] as? Int) ?: 0)
        editor.putInt("lastAge", (surveyResponses["age"] as? Int) ?: 0)
        editor.putInt("lastPhysicalActivity", (surveyResponses["physicalActivity"] as? Int) ?: 0)
        editor.putInt("lastStressLevel", (surveyResponses["stressLevel"] as? Int) ?: 0)
        editor.putInt("lastSleepDuration", (surveyResponses["sleepDuration"] as? Int) ?: 0)

        editor.apply()
        Log.d("SurveyActivity", "Survey responses saved to preferences.")
    }


    private fun sendDataToApi() {
        saveInputToPreferences()

        val gender = (surveyResponses["gender"] as? Int) ?: 0
        val age = (surveyResponses["age"] as? Int) ?: 0
        val physicalActivity = (surveyResponses["physicalActivity"] as? Int) ?: 0
        val stressLevel = (surveyResponses["stressLevel"] as? Int) ?: 0
        val sleepDuration = (surveyResponses["sleepDuration"] as? Int) ?: 0

        Log.d("SurveyActivity", "Sending to API - Gender: $gender, Age: $age, Physical Activity: $physicalActivity, Stress Level: $stressLevel, Sleep Duration: $sleepDuration")

        val qualityRequest = SleepQualityRequest(gender, age, physicalActivity, stressLevel, sleepDuration)
        val durationRequest = SleepDurationRequest(gender, age, physicalActivity, stressLevel)

        ApiConfig.getApiService().predictSleepQuality(qualityRequest).enqueue(object : Callback<SleepQualityResponse> {
            override fun onResponse(call: Call<SleepQualityResponse>, response: Response<SleepQualityResponse>) {
                if (response.isSuccessful) {
                    val prediction = response.body()?.prediction ?: 0f
                    savePredictionToPreferences("quality", prediction)
                    Log.d("SurveyActivity", "Sleep Quality Prediction: $prediction")
                } else {
                    Log.e("SurveyActivity", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<SleepQualityResponse>, t: Throwable) {
                Log.e("SurveyActivity", "Failed to connect to API: ${t.message}")
            }
        })

        ApiConfig.getApiService().predictSleepDuration(durationRequest).enqueue(object : Callback<SleepDurationResponse> {
            override fun onResponse(call: Call<SleepDurationResponse>, response: Response<SleepDurationResponse>) {
                if (response.isSuccessful) {
                    val prediction = response.body()?.prediction ?: 0f
                    savePredictionToPreferences("duration", prediction)
                    Log.d("SurveyActivity", "Sleep Duration Prediction: $prediction")
                } else {
                    Log.e("SurveyActivity", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<SleepDurationResponse>, t: Throwable) {
                Log.e("SurveyActivity", "Failed to connect to API: ${t.message}")
            }
        })
    }


    private fun savePredictionToPreferences(key: String, value: Float) {
        val sharedPreferences = getSharedPreferences("EepyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    private fun navigateToNextPage() {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    private fun saveResponse(key: String, value: Any) {
        surveyResponses[key] = value
        Log.d("SurveyActivity", "Current Responses: $surveyResponses")
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun isSurveyDoneToday(): Boolean {
        val sharedPreferences = getSharedPreferences("EepyPreferences", Context.MODE_PRIVATE)
        val lastDate = sharedPreferences.getString("lastSurveyDate", null)
        val currentDate = getCurrentDate()
        return lastDate == currentDate
    }


    private fun saveSurveyDate() {
        val sharedPreferences = getSharedPreferences("EepyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("lastSurveyDate", getCurrentDate())
        editor.apply()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}