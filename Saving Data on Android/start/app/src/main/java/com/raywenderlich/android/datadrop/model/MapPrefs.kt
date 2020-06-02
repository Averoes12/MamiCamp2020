package com.raywenderlich.android.datadrop.model

import android.preference.PreferenceManager
import com.raywenderlich.android.datadrop.app.DataDropApplication
import com.raywenderlich.android.datadrop.ui.map.MarkerColor.Companion.RED_COLOR

object MapPrefs {

    private const val KEY_MARKER_COLOR = "KEY MARKER COLOR"
    private const val KEY_MAP_TYPE = "KEY_MAP_TYPE"

    fun sharePref() = PreferenceManager.getDefaultSharedPreferences(DataDropApplication.getAppContext())

    fun saveMarkerColor(markerColor:String){
        val editor = sharePref().edit()
        editor.putString(KEY_MARKER_COLOR, markerColor).apply()
    }

    fun getMakerColor():String? = sharePref().getString(KEY_MARKER_COLOR, RED_COLOR)

    fun saveMapType(mapType:String){
        val editor = sharePref().edit()
        editor.putString(KEY_MAP_TYPE, mapType).apply()
    }

    fun getMapType():String = sharePref().getString(KEY_MAP_TYPE, "Normal")
}