package com.example.umbrella.model.datasource.local.hourlyweather


import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sys : Parcelable {

    @SerializedName("type")
    @Expose
    var type: Int? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("message")
    @Expose
    var message: Float? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int? = null
    @SerializedName("sunset")
    @Expose
    var sunset: Int? = null

    protected constructor(`in`: Parcel) {
        this.type = `in`.readValue(Int::class.java.classLoader) as Int
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.message = `in`.readValue(Float::class.java.classLoader) as Float
        this.country = `in`.readValue(String::class.java.classLoader) as String
        this.sunrise = `in`.readValue(Int::class.java.classLoader) as Int
        this.sunset = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param message
     * @param id
     * @param sunset
     * @param sunrise
     * @param type
     * @param country
     */
    constructor(type: Int?, id: Int?, message: Float?, country: String, sunrise: Int?, sunset: Int?) : super() {
        this.type = type
        this.id = id
        this.message = message
        this.country = country
        this.sunrise = sunrise
        this.sunset = sunset
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(type)
        dest.writeValue(id)
        dest.writeValue(message)
        dest.writeValue(country)
        dest.writeValue(sunrise)
        dest.writeValue(sunset)
    }

    override fun describeContents(): Int {
        return 0
    }
//
//    companion object {
//        val CREATOR: Parcelable.Creator<Sys> = object : Creator<Sys> {
//
//
//            override fun createFromParcel(`in`: Parcel): Sys {
//                return Sys(`in`)
//            }
//
//            override fun newArray(size: Int): Array<Sys> {
//                return arrayOfNulls(size)
//            }
//
//        }
//    }

    companion object CREATOR : Creator<Sys> {
        override fun createFromParcel(parcel: Parcel): Sys {
            return Sys(parcel)
        }

        override fun newArray(size: Int): Array<Sys?> {
            return arrayOfNulls(size)
        }
    }

}
