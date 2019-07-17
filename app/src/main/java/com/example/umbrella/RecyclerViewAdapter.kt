package com.example.umbrella

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umbrella.model.datasource.local.daysforecast.X
import com.example.umbrella.model.datasource.local.daysforecast.fivedayResponse
import kotlinx.android.synthetic.main.activity_weather.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat

class RecyclerViewAdapter(val item: List<X>, val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var date = item[position].dtTxt
        date = convertToDay(date) // comment this out and it work

        Log.d("TAGGGE", date)


        var mTemp = item[position].main.temp
        mTemp = convertToFahrenheit(mTemp)

        Log.d("TaGG", mTemp.toString())
        var mdescription = item[position].weather[0].description
        mdescription = mdescription.toUpperCase()

        holder.itemView.tv_day.text = date
        holder.itemView.tv_temp.text = "%.0f".format(mTemp).toString() + "°"
        holder.itemView.tv_description.text = mdescription
        Log.d("TAGgs", mdescription)

    }

    private fun convertToDay(date: String): String {
        var inputDate = date
        var format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var dt1 = format1.parse(inputDate)
        var format2 = SimpleDateFormat("E,MMMM dd \nhh:mma")
        var finalDay = format2.format(dt1)
        Log.d("TAGS", "$finalDay")
        return finalDay
    }

    private fun convertToFahrenheit(temp: Double?): Double {
        return ((temp!! - 273.15) * 9 / 5 + 32)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val  tvMyCurrentTemp = view.tvCurrentTemp
    }
}

