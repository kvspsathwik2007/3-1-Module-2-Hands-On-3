package com.example.a3_1_module2handson3.repository

import com.example.a3_1_module2handson3.model.PromptRequest
import com.example.a3_1_module2handson3.model.PromptResponse
import com.example.a3_1_module2handson3.network.PromptApi

class PromptRepository(
    private val api: PromptApi
) {

    suspend fun comparePrompts(
        task: String
    ): Result<PromptResponse> {

        return try {

            val response = api.comparePrompts(
                PromptRequest(task)
            )

            Result.success(response)

        } catch (exception: Exception) {

            Result.failure(exception)
        }
    }
}