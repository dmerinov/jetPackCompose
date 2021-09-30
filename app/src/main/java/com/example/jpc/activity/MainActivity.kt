package com.example.jpc.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.example.jpc.data.SampleData
import com.example.jpc.uiElements.Conversation
import com.example.jpc.model.Message
import com.example.jpc.presenter.MainPresenter
import com.example.jpc.presenter.MainView
import com.example.jpc.ui.theme.JpcTheme

class MainActivity : ComponentActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val presenter = MainPresenter(this)

        setContent {
            JpcTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //this warning is triggered due to the use of the pic loader methods.
                    presenter.LoadMessages()
                }
            }
        }
    }

    @ExperimentalCoilApi
    @Composable
    override fun LoadMessages(data: List<Message>) {
        Conversation(data)
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun PreviewConversation() {
    JpcTheme {
        Conversation(SampleData.conversationSample)
    }
}