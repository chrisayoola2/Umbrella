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
}
