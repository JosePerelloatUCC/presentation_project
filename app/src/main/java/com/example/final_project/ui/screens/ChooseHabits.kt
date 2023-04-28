package com.example.finalproject.ui.Screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.*


@Composable
fun ScreenHabits(
    modifier: Modifier = Modifier,
    //projectViewModel: ProjectViewModel = viewModel()

) {

}




@Composable
fun ChooseHabits(
    userhabit:String,
    notificationTime:Int,
    userAlarm:Boolean,
    onSaveClick: (Context, String, Int, Boolean) -> Unit
) {
    val context = LocalContext.current

    var descriptionText by remember {
        mutableStateOf("")
    }

    var timeText by remember {
        mutableStateOf("")
    }

    var notify by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = { Text("Adding a new Habit.")},
        backgroundColor = Color.Black,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = {/*TODO*/}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        }
    )



    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = descriptionText,
            onValueChange = { descriptionText = it },
            label = { Text(text = "Enter your Habit")})
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = timeText, onValueChange = { timeText = it },
            label = { Text(text = "Time for Notification")})
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Do you want to be notified?")
        Switch(checked = notify, onCheckedChange = { notify = it })
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick =  { onSaveClick(context, descriptionText, timeText.toInt(), notify) }) {
            Text("Save your Habit")


        }

    }

}
