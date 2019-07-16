package com.example.umbrella

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umbrella.model.datasource.local.daysforecast.fivedayResponse
import com.example.umbrella.model.datasource.local.hourlyweather.MyWeather
import com.example.umbrella.model.datasource.remote.WeatherService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {
    private var cityField: TextView? = null
    private lateinit var enteredZip: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        cityField = findViewById(R.id.city_field)
        enteredZip = intent.getStringExtra("zipkey")
        getTheCurrentWeather()
        getForeCastWeather()
    }

    internal fun getForeCastWeather() {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getForecastData(enteredZip, AppId)
        call.enqueue(object : Callback<fivedayResponse> {

            override fun onResponse(call: Call<fivedayResponse>, response: Response<fivedayResponse>) {
                if (response.code() == 200) {
                    val myForecast = response.body()
                    rv_weather_recyclerView.layoutManager = LinearLayoutManager(this@WeatherActivity)
                    rv_weather_recyclerView.adapter = RecyclerViewAdapter(myForecast!!.list,this@WeatherActivity)
                   // RecyclerViewAdapter(myForecast!!.list,this@WeatherActivity)

                    Log.d("TAGS", "${myForecast?.list!!.get(0).main.temp}\n")
                    Log.d("TAGS", "${myForecast?.list!!.get(0).dt}")
                    Log.d("TAGS", "${myForecast?.list!!.get(0).dtTxt}")
                    Log.d("TAGS", "${myForecast?.list!!.get(1).main.temp}\n")
                    Log.d("TAGS", "${myForecast?.list!!.get(1).dt}")
                    Log.d("TAGS", "${myForecast?.list!!.get(1).dtTxt.format(Locale.US)}")

                    val input_date = myForecast?.list!![10].dtTxt
                    val format1 = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
                    val dt1 = format1.parse(input_date)
                    val format2 = SimpleDateFormat("EEEE")
                    val finalDay = format2.format(dt1)
                    Log.d("TAGS", "$finalDay")
                }
            }

            override fun onFailure(call: Call<fivedayResponse>, t: Throwable) {
                cityField?.text = t.message
            }
        })

    }
    internal fun getTheCurrentWeather() {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getWeatherData(enteredZip, AppId)
        call.enqueue(object : Callback<MyWeather> {


            override fun onResponse(call: Call<MyWeather>, response: Response<MyWeather>) {
                if (response.code() == 200) {
                    val myWeather = response.body()
                    cityField?.text = myWeather?.name // gets

                    var currentTemp = myWeather!!.main!!.temp
                    currentTemp = convertToFahrenheit(currentTemp) // converts current temp from Kelvin to Degrees
                    if (currentTemp > 60) {
                        // tvCurrentTemp.setTextColor(R.color.colorHeat)
                        tvCurrentTemp.setTextColor(resources.getColor(R.color.colorHeat))
                    } else tvCurrentTemp.setTextColor(resources.getColor(R.color.colorCool))
                    tvCurrentTemp.text = "%.0f".format(currentTemp).toString() + "°"
                }
            }

            override fun onFailure(call: Call<MyWeather>, t: Throwable) {
                cityField?.text = t.message
            }

        })
    }

    private fun convertToFahrenheit(temp: Float?): Float {
        return ((temp!! - 273.15) * 9 / 5 + 32).toFloat()
    }

    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "a3f4403861102311554ff6b93a5ce529"
    }
}
