package com.example.finalproject.ui.Screens

import android.content.Intent
import android.graphics.Paint.Style
import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.drawable.Icon
import android.icu.lang.UCharacter.VerticalOrientation
import android.media.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.final_project.ui.Habit


@Composable
fun ListofHabits(
    habits:List<Habit>,
    onCreatenewHabitClick: () -> Unit,
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Main1Screen")
        putExtra(Intent.EXTRA_TEXT, "test")
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(

                title = { Text("Your List of Habits")},
                backgroundColor = Color.Black,
                contentColor = Color.White


            )
        },
        /*floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 16.dp),
                onClick = { onCreatenewHabitClick }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White)

            }
        }*/

        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreatenewHabitClick,
                modifier = Modifier.padding(all = 16.dp),
                backgroundColor = Color.White,
                contentColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add",
                    tint = Color.Black
                )
            }
        },
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {

            }
        },
        //item index, pass the list of habits
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            itemsIndexed(habits) { idx, hbt ->
                HabitCard(habit = hbt, index = idx)
            }
        }
    }
    Column() {

    }


}
//go back to first screen when save optioSn.
@Composable
fun HabitCard (
    habit: Habit,
    index: Int,
) {
    var notification:String
    if (habit.isNotified == false) {
        notification = "Notifications are not activated"
    } else {
        notification = "Notifications are activated"
    }
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        backgroundColor = Color.Gray,
        contentColor = Color.White
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = habit.description,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "*Description for Habit*",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Notification at ${habit.notificationHour.toString()}",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification,
                style = MaterialTheme.typography.body2)
            Box(
                Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Edit, contentDescription = null)
                    }
                }
            }
        }
    }
}