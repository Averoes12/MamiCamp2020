package com.averoes.listmaker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.listmaker.adapter.TodoListAdapter
import com.averoes.listmaker.utils.ListDataManager
import com.averoes.listmaker.utils.TaskList
import kotlinx.android.synthetic.main.fragment_task_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment(), TodoListAdapter.TodoClickListener {
    // TODO: Rename and change types of parameters

    private lateinit var adapter: TodoListAdapter
    private lateinit var listDataManager: ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listDataManager = ViewModelProviders.of(this).get(ListDataManager::class.java)

        val lists = listDataManager.readList()
        list_recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = TodoListAdapter(lists,this)
        list_recyclerView.adapter = adapter

        fab.setOnClickListener {
            showDialog()
        }

    }


    companion object{
        fun newInstance():TaskListFragment{
            return newInstance()
        }
    }

    override fun itemClicked(list: TaskList) {
        showTaskListItem(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = list_recyclerView.adapter as TodoListAdapter
        todoAdapter.addItem(list)

    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateList()

    }

    private fun updateList() {
        val lists = listDataManager.readList()
        list_recyclerView.adapter = TodoListAdapter(lists, this)
    }

    private fun showDialog() {
        activity?.let {
            val alertDialog = AlertDialog.Builder(it)
            val title = getString(R.string.add_todo_dialog)

            val inputTodo = EditText(it)
            inputTodo.hint = getString(R.string.hint_dialog)
            inputTodo.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            alertDialog.setTitle(title)
            alertDialog.setView(inputTodo)
            alertDialog.setPositiveButton(getString(R.string.positive_button_dialog)) { dialog, _ ->
                val list = TaskList(inputTodo.text.toString())
                addList(list)
                showTaskListItem(list)
                dialog.dismiss()
            }
            alertDialog.show().create()
        }
    }

    private fun showTaskListItem(list: TaskList) {
        view?.let {
            val action = TaskListFragmentDirections.actionTaskListFragmentToDetailFragment(list.name)
            it.findNavController().navigate(action)
        }
    }
}
