package com.averoes.listmaker.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class ListDataManager(application: Application) : AndroidViewModel(application) {

    val context = application.applicationContext

    fun saveList(list:TaskList){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name, list.task.toHashSet())
        sharedPreferences.apply()
    }

    fun readList():ArrayList<TaskList>{
        val sharedPress = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPress.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in contents){
            val taskItem = ArrayList(taskList.value  as HashSet<String>)
            val item = TaskList(taskList.key, taskItem)
            taskLists.add(item)
        }

        return taskLists

     }
}