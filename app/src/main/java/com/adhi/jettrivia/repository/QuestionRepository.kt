package com.adhi.jettrivia.repository

import com.adhi.jettrivia.data.DataOrException
import com.adhi.jettrivia.model.QuestionItem
import com.adhi.jettrivia.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionAPI
) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>,
            Boolean,
            Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loadingState = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loadingState = false

        } catch (exception: Exception) {
            dataOrException.e
        }
        return dataOrException
    }
}
