package com.example.a3_1_module2handson3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3_1_module2handson3.model.PromptResult
import com.example.a3_1_module2handson3.network.RetrofitClient
import com.example.a3_1_module2handson3.repository.PromptRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PromptViewModel : ViewModel() {

    private val repository =
        PromptRepository(RetrofitClient.api)

    private val _results =
        MutableStateFlow<List<PromptResult>>(emptyList())

    val results: StateFlow<List<PromptResult>>
        get() = _results

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    private val _error =
        MutableStateFlow<String?>(null)

    val error: StateFlow<String?>
        get() = _error


    fun compare(task: String) {

        val cleanTask = task.trim()

        if (cleanTask.isEmpty()) {

            _error.value = "Please enter a task."

            return
        }

        _results.value = emptyList()
        _error.value = null
        _isLoading.value = true


        viewModelScope.launch {

            val result =
                repository.comparePrompts(cleanTask)


            result.onSuccess { response ->

                _results.value = response.results

            }.onFailure { exception ->

                _error.value =
                    getErrorMessage(exception)
            }


            _isLoading.value = false
        }
    }


    private fun getErrorMessage(
        exception: Throwable
    ): String {

        val message =
            exception.message.orEmpty()


        return when {

            message.contains(
                "timeout",
                ignoreCase = true
            ) -> {

                "Request timed out. Please try again."
            }


            message.contains(
                "Unable to resolve host",
                ignoreCase = true
            ) -> {

                "Backend server could not be reached."
            }


            message.contains(
                "Connection refused",
                ignoreCase = true
            ) -> {

                "Flask backend is not running. Start the backend on port 5000."
            }


            else -> {

                "Could not connect to the backend. " +
                        "Make sure Flask is running on port 5000."
            }
        }
    }


    fun clearError() {

        _error.value = null
    }
}