package com.raywenderlich.android.datadrop.model

import android.arch.persistence.room.TypeConverter
import com.google.android.gms.maps.model.LatLng
import java.util.*

class LatLngConverter {
    @TypeConverter
    fun fromLatLng(latlng:LatLng?) : String?{
        if (latlng != null){
            return String.format(Locale.US, "%f,%f", latlng.latitude, latlng.longitude)
        }
        return null
    }

    @TypeConverter
    fun toLatLng(value:String?):LatLng?{
        if (value != null){
            val pieces = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return LatLng(java.lang.Double.parseDouble(pieces[0]), java.lang.Double.parseDouble(pieces[1]))
        }
        return null
    }
}