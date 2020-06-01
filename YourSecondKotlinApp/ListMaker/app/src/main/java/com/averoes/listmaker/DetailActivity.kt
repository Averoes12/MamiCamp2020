package com.averoes.listmaker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.listmaker.adapter.TaskListAdapter
import com.averoes.listmaker.utils.TaskList
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



    }



}
