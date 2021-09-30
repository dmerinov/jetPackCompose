package com.example.jpc.presenter

import androidx.compose.runtime.Composable
import com.example.jpc.data.SampleData
import com.example.jpc.model.Message

class MainPresenter(val view: MainView) {

    @Composable
    fun LoadMessages(){
        view.LoadMessages(data = SampleData.conversationSample)
    }
}

interface MainView {
    @Composable
    fun LoadMessages(data: List<Message>)
}