package com.example.umbrella.model.datasource.local.hourlyweather


import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord : Parcelable {

    @SerializedName("lon")
    @Expose
    var lon: Float? = null
    @SerializedName("lat")
    @Expose
    var lat: Float? = null

    protected constructor(`in`: Parcel) {
        this.lon = `in`.readValue(Float::class.java.classLoader) as Float
        this.lat = `in`.readValue(Float::class.java.classLoader) as Float
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param lon
     * @param lat
     */
    constructor(lon: Float?, lat: Float?) : super() {
        this.lon = lon
        this.lat = lat
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(lon)
        dest.writeValue(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

//    companion object {
//        val CREATOR: Parcelable.Creator<Coord> = object : Creator<Coord> {
//
//
//            override fun createFromParcel(`in`: Parcel): Coord {
//                return Coord(`in`)
//            }
//
//            override fun newArray(size: Int): Array<Coord> {
//                return arrayOfNulls(size)
//            }
//
//        }
//    }

    companion object CREATOR : Creator<Coord> {
        override fun createFromParcel(parcel: Parcel): Coord {
            return Coord(parcel)
        }

        override fun newArray(size: Int): Array<Coord?> {
            return arrayOfNulls(size)
        }
    }

}
