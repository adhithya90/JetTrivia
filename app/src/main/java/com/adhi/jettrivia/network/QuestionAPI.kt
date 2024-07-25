package com.adhi.jettrivia.network

import com.adhi.jettrivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface QuestionAPI {
    @GET("world.json")
    suspend fun getAllQuestions(): Question
}