package com.example.a3_1_module2handson3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_1_module2handson3.adapter.PromptResultAdapter
import com.example.a3_1_module2handson3.viewmodel.PromptViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PromptViewModel

    private lateinit var taskInput: EditText
    private lateinit var compareButton: Button
    private lateinit var loadingContainer: LinearLayout
    private lateinit var loadingText: TextView
    private lateinit var resultsRecyclerView: RecyclerView
    private lateinit var promptResultAdapter: PromptResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[PromptViewModel::class.java]

        initializeViews()
        setupRecyclerView()
        setupButton()
        observeViewModel()
    }

    private fun initializeViews() {

        taskInput = findViewById(R.id.etTask)
        compareButton = findViewById(R.id.btnCompare)
        loadingContainer = findViewById(R.id.loadingContainer)
        loadingText = findViewById(R.id.tvLoading)
        resultsRecyclerView = findViewById(R.id.rvResults)
    }

    private fun setupRecyclerView() {

        promptResultAdapter = PromptResultAdapter()

        resultsRecyclerView.layoutManager =
            LinearLayoutManager(this)

        resultsRecyclerView.adapter =
            promptResultAdapter

        resultsRecyclerView.isNestedScrollingEnabled = false
        resultsRecyclerView.overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun setupButton() {

        compareButton.setOnClickListener {

            val task = taskInput.text
                .toString()
                .trim()

            if (task.isEmpty()) {

                taskInput.error = "Please enter a task"
                taskInput.requestFocus()

                return@setOnClickListener
            }

            taskInput.error = null

            viewModel.compare(task)
        }
    }

    private fun observeViewModel() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.results.collect { results ->
                        promptResultAdapter.submitList(results)
                    }
                }

                launch {
                    viewModel.isLoading.collect { loading ->
                        updateLoadingState(loading)
                    }
                }

                launch {
                    viewModel.error.collect { error ->

                        if (!error.isNullOrBlank()) {

                            showError(error)
                            viewModel.clearError()
                        }
                    }
                }
            }
        }
    }

    private fun updateLoadingState(
        isLoading: Boolean
    ) {

        if (isLoading) {

            loadingContainer.visibility = View.VISIBLE
            loadingText.text = "Comparing prompts..."

            compareButton.isEnabled = false
            compareButton.text = "Comparing..."

        } else {

            loadingContainer.visibility = View.GONE

            compareButton.isEnabled = true
            compareButton.text = "Compare Prompts"
        }
    }

    private fun showError(message: String) {

        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}