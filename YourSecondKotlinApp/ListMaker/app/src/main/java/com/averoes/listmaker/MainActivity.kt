package com.averoes.listmaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.listmaker.adapter.TodoListAdapter
import com.averoes.listmaker.utils.ListDataManager
import com.averoes.listmaker.utils.TaskList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        Navigation.findNavController(this, R.id.nav_host_fragment)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        toolbar.title = getString(R.string.listmaker)
    }

}
