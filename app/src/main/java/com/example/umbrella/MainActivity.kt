package com.example.umbrella

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.SharedPreferences
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    //   private var cityField: TextView? = null

    //a3f4403861102311554ff6b93a5ce529

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkForZip() // checks if zip is saved in saved preference

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnEnter -> {
                if (etZipCode.text.isNotEmpty()) {
                    val zipCode = etZipCode.text.toString()
                    val intent = Intent(this, WeatherActivity::class.java)
                    intent.putExtra("zipkey", zipCode)
                    val editor = getPreferences(Context.MODE_PRIVATE).edit().putString("saved_zip", zipCode)
                        .commit() //Saves zipcode to shared preference
                    if (editor) {
                        Toast.makeText(this, "ZipCode : $zipCode Saved", Toast.LENGTH_SHORT)
                            .show() // displays if zip saved to shared preference
                    }
                    startActivity(intent)
                }

            }
            else -> {
            }
        }
    }

    private fun checkForZip() {
        var savedZip = getPreferences(Context.MODE_PRIVATE).getString("saved_zip", null)
        if (savedZip != null) {
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("zipkey", savedZip)
            startActivity(intent)

        }
    }


//    internal fun getTheCurrentWeather(){
//        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
//        val service = retrofit.create(WeatherService::class.java)
//        val call = service.getWeatherData(zip, AppId)
//        call.enqueue(object : Callback<MyWeather>{
//
//
//            override fun onResponse(call: Call<MyWeather>, response: Response<MyWeather>) {
//              if(response.code() == 200){
//                  val myWeather = response.body()
//                  cityField?.text = myWeather?.name // gets
//
//                  var currentTemp = myWeather!!.main!!.temp
//                  currentTemp = convertToFahrenheit(currentTemp) // converts current temp from Kelvin to Degrees
//                  if (currentTemp > 60){
//                     // tvCurrentTemp.setTextColor(R.color.colorHeat)
//                      tvCurrentTemp.setTextColor(resources.getColor(R.color.colorHeat))
//                  }else tvCurrentTemp.setTextColor(resources.getColor(R.color.colorCool))
//                  tvCurrentTemp.text = "%.0f".format(currentTemp).toString() + "°"
//
//
//
//              }
//            }
//
//            override fun onFailure(call: Call<MyWeather>, t: Throwable) {
//                 cityField?.text= t.message
//            }
//
//        })
//    }
//
//    private fun convertToFahrenheit(temp : Float?) : Float{
//            return ((temp!! - 273.15)*9/5+32).toFloat()
//    }
//
//
//
//    companion object{
//        var BaseUrl = "http://api.openweathermap.org/"
//        var AppId = "a3f4403861102311554ff6b93a5ce529"
//        var zip = "77084"
//        val stringBuilder =
//            "Country: " +
//                    myWeather?.sys!!.country +
//                    "\n" +
//                    "Temperature: " +
//                    myWeather.main!!.temp +
//                    "\n" +
//                    "Temperature(Min): " +
//                    myWeather.main!!.tempMin +
//                    "\n" +
//                    "Temperature(Max): " +
//                    myWeather.main!!.tempMax +
//                    "\n" +
//                    "Humidity: " +
//                    myWeather.main!!.humidity +
//                    "\n" +
//                    "Pressure: " +
//                    myWeather.main!!.pressure +
//                    "\n" +
//                    "Location: " + myWeather.name
//
//        //  c!!.text = stringBuilder
}
