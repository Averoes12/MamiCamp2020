package com.raywenderlich.android.rwdc2018.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.raywenderlich.android.rwdc2018.app.PhotosUtils

class PhotoJobServie : JobService() {

    companion object{
        private const val TAG = "PhotoJobService"
    }
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.i(TAG, "Job Stopped ${params?.jobId}")
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val runnable = Runnable {
            val needReSchedule:Boolean
            needReSchedule = try {
                val jsonString = PhotosUtils.photoJsonString()
                (jsonString == null)
            }catch (e:InterruptedException){
                Log.e(TAG, "Error running job ${e.message}")
                true
            }
            Log.i(TAG, "Job has finished: ${params?.jobId}, needs Reschedule $needReSchedule")
            jobFinished(params, needReSchedule)
        }

        Thread(runnable).start()
        return true
    }

}
