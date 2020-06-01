package com.raywenderlich.android.rwdc2018.service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.raywenderlich.android.rwdc2018.app.PhotosUtils
import com.raywenderlich.android.rwdc2018.app.RWDC2018Application

class FetchIntentService : IntentService("FetchIntentService") {

    companion object{
        private const val TAG = "FetchIntentService"
        private const val ACTION_FETCH = "ACTOION_FETCH"
        const val FETCH_COMPLETE = "FETCH_COMPLETE"

        fun starActionFetch(context: Context){
            val intent = Intent(context, FetchIntentService::class.java).apply {
                action = ACTION_FETCH
            }
            context.startService(intent)
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        when(intent?.action){
            ACTION_FETCH -> {
                hanldeActionFetch()
            }
        }
    }
    private fun hanldeActionFetch(){
        try {
            PhotosUtils.fetchJsonString()
        }catch (e:InterruptedException){
            Log.e(TAG, "Error Downloading ${e.message}")
        }
        broadcatFetchComplete()
    }

    private fun broadcatFetchComplete(){
        val intent = Intent(FETCH_COMPLETE)
        LocalBroadcastManager.getInstance(RWDC2018Application.getAppContext()).sendBroadcast(intent)
    }
}
