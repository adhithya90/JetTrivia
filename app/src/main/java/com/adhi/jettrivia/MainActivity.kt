package com.adhi.jettrivia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.adhi.jettrivia.model.QuestionItem
import com.adhi.jettrivia.repository.QuestionRepository
import com.adhi.jettrivia.screens.QuestionsViewModel
import com.adhi.jettrivia.ui.theme.JetTriviaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetTriviaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(vertical = 24.dp)
                    ) {
                        TriviaHome()
                    }
                }
            }
        }
    }
}

@Composable
fun TriviaHome(viewModel: QuestionsViewModel = hiltViewModel()) {
    Questions(viewModel)
}

@Composable
fun QuestionDisplay(questions: MutableList<QuestionItem>) {
    //iterate through the list of questions using lazy column
    LazyColumn {
        items(questions.size) { index ->
            Row {
                Text(text = "Q: ", modifier = Modifier.padding(top = 10.dp))
                Text(text = questions[index].question)
            }
        }
    }
}

@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loadingState == true) {
        // ** TODO ** Add a circular progress indicator or similar
        Text(text = "Loading...")
    } else {
        if (questions != null) {
            QuestionDisplay(questions = questions)

        }
    }
}

