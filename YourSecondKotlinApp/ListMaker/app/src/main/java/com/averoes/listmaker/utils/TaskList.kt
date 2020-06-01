package com.averoes.listmaker.utils

import android.os.Parcel
import android.os.Parcelable


class TaskList(val name:String, var task:ArrayList<String> = ArrayList()) :Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeStringList(task)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel): TaskList {
            return TaskList(parcel)
        }

        override fun newArray(size: Int): Array<TaskList?> {
            return arrayOfNulls(size)
        }
    }
}