package com.example.umbrella.model.datasource.local.hourlyweather


import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main : Parcelable {

    @SerializedName("temp")
    @Expose
    var temp: Float? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null
    @SerializedName("temp_min")
    @Expose
    var tempMin: Float? = null
    @SerializedName("temp_max")
    @Expose
    var tempMax: Float? = null

    protected constructor(`in`: Parcel) {
        this.temp = `in`.readValue(Float::class.java.classLoader) as Float
        this.pressure = `in`.readValue(Int::class.java.classLoader) as Int
        this.humidity = `in`.readValue(Int::class.java.classLoader) as Int
        this.tempMin = `in`.readValue(Float::class.java.classLoader) as Float
        this.tempMax = `in`.readValue(Float::class.java.classLoader) as Float
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    constructor(temp: Float?, pressure: Int?, humidity: Int?, tempMin: Float?, tempMax: Float?) : super() {
        this.temp = temp
        this.pressure = pressure
        this.humidity = humidity
        this.tempMin = tempMin
        this.tempMax = tempMax
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(temp)
        dest.writeValue(pressure)
        dest.writeValue(humidity)
        dest.writeValue(tempMin)
        dest.writeValue(tempMax)
    }

    override fun describeContents(): Int {
        return 0
    }

//    companion object {
//        val CREATOR: Parcelable.Creator<Main> = object : Creator<Main> {
//
//
//            override fun createFromParcel(`in`: Parcel): Main {
//                return Main(`in`)
//            }
//
//            override fun newArray(size: Int): Array<Main> {
//                return arrayOfNulls(size)
//            }
//
//        }
 //   }

    companion object CREATOR : Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }

}
