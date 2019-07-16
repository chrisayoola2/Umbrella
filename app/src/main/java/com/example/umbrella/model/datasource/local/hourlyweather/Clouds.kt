package com.example.umbrella.model.datasource.local.hourlyweather


import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Clouds : Parcelable {

    @SerializedName("all")
    @Expose
    var all: Int? = null

    protected constructor(`in`: Parcel) {
        this.all = `in`.readValue(Int::class.java.classLoader) as Int
    }

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param all
     */
    constructor(all: Int?) : super() {
        this.all = all
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(all)
    }

    override fun describeContents(): Int {
        return 0
    }

//    companion object {
//        val CREATOR: Parcelable.Creator<Clouds> = object : Creator<Clouds> {
//
//
//            override fun createFromParcel(`in`: Parcel): Clouds {
//                return Clouds(`in`)
//            }
//
//            override fun newArray(size: Int): Array<Clouds?> {
//                return arrayOfNulls(size)
//            }
//
//        }
//    }

    companion object CREATOR : Creator<Clouds> {
        override fun createFromParcel(parcel: Parcel): Clouds {
            return Clouds(parcel)
        }

        override fun newArray(size: Int): Array<Clouds?> {
            return arrayOfNulls(size)
        }
    }

}
