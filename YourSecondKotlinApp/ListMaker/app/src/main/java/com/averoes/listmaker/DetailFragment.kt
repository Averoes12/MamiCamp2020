package com.averoes.listmaker

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.averoes.listmaker.adapter.TaskListAdapter
import com.averoes.listmaker.utils.ListDataManager
import com.averoes.listmaker.utils.TaskList
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    lateinit var list:TaskList
    lateinit var adapter: TaskListAdapter
    lateinit var listDataManager: ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        listDataManager = ViewModelProviders.of(this).get(ListDataManager::class.java)

        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            list = listDataManager.readList().filter {
                it.name == args.listString
            }[0]
        }

        activity?.let {

            task_list_recylerview.layoutManager = LinearLayoutManager(context)
            adapter = TaskListAdapter(list)
            task_list_recylerview.adapter = adapter

            it.toolbar?.title = list.name

            add_task.setOnClickListener {
                showCreateDialog()
            }

        }

    }

    companion object {

        private val ARG_LIST = "list"
        fun newInstance(list:TaskList) : DetailFragment{
            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST, list)
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun showCreateDialog(){
        activity?.let {
            val taskInput = EditText(it)
            taskInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            taskInput.hint = getString(R.string.hint_dialog)
            AlertDialog.Builder(it)
                .setTitle(R.string.add_todo_dialog)
                .setView(taskInput)
                .setPositiveButton(R.string.positive_button_dialog){
                        dialog, _ ->

                    val task = taskInput.text.toString()
                    list.task.add(task)
                    listDataManager.saveList(list)
                    dialog.dismiss()
                }
                .create().show()
        }


    }

}
