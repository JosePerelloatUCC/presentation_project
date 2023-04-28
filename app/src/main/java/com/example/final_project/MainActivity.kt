package com.example.final_project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.final_project.ui.HabitApp
import com.example.final_project.ui.theme.Final_projectTheme
import com.example.finalproject.ui.Screens.ChooseHabits
import com.example.finalproject.ui.Screens.ListofHabits

val CHANNEL_ID = "channel1"
private fun reminderNotifcationChannel(applicationContext: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name = "NotificatioonChannelName"
        val descriptionText = "NotificationChannelDes"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Final_projectTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HabitApp()
                }

            }
        }
    }
}
