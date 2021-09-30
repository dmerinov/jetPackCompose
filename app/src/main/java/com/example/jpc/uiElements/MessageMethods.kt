package com.example.jpc.uiElements

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.jpc.data.SampleData
import com.example.jpc.model.Message

@ExperimentalCoilApi
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MessageCard(msg: Message) {
    //a row is needed here in order to show the pfp and the msg in the same line.
    Row(modifier = Modifier.padding(all = 8.dp)) {
        SetImage(img = "https://i.pinimg.com/originals/0a/e4/b8/0ae4b805d0aa39fc7562ba6cc17af4ef.jpg")
        SetMessage(msg = msg)
    }
}

@ExperimentalCoilApi
@Composable
fun SetImage(img: String) {
    Image(
        painter = rememberImagePainter(img),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun SetMessage(msg: Message) {
    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember { mutableStateOf(false) }
    // surfaceColor will be updated gradually from one color to the other
    val surfaceColor: Color by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
    )
    // We toggle the isExpanded variable when we click on this Column
    Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
        Text(
            text = msg.author,
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(4.dp))
        //this surface will put the white background in the messages
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            // surfaceColor color will be changing gradually from primary to surface
            color = surfaceColor,
            // animateContentSize will change the Surface size gradually
            modifier = Modifier
                .animateContentSize()
                .padding(1.dp)
        ) {
            Text(
                text = msg.body,
                modifier = Modifier.padding(all = 4.dp),
                // If the message is expanded, we display all its content
                // otherwise we only display the first line
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    Conversation(SampleData.conversationSample)
}