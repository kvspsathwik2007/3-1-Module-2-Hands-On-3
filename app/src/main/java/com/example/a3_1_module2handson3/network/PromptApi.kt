package com.example.a3_1_module2handson3.network

import com.example.a3_1_module2handson3.model.PromptRequest
import com.example.a3_1_module2handson3.model.PromptResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PromptApi {

    @POST("prompt/compare")
    suspend fun comparePrompts(
        @Body request: PromptRequest
    ): PromptResponse
}