package com.example.final_project.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import java.util.*


data class FinalProjectUIState(
    val habitDescription: String = "",
    val timeofNotification: Int = -1,
    val userNotification: Boolean = false
)

data class Habit(
    val description: String,
    val notificationHour: Int,
    val isNotified: Boolean,
    val userCompleted: List<Date> = listOf()
)

class ProjectViewModel : ViewModel() {
    private val _habitList: MutableList<Habit> = mutableListOf<Habit>()
    var habits: List<Habit> = _habitList.toList()

    private val _uiState = MutableStateFlow(FinalProjectUIState())
    val uiState: StateFlow<FinalProjectUIState> = _uiState.asStateFlow() //read-only

    fun saveHabitfromUser(context: Context, name: String, time: Int, notification: Boolean) {
        val habitz = context.getSharedPreferences("user_habits", Context.MODE_PRIVATE)
        /*habitz.edit().putString("Habit_name", name).apply()
        habitz.edit().putInt("Time_for_notification", time).apply()
        habitz.edit().putBoolean("Set_reminder", notification).apply()*/

        val newHabit = Habit(name, time, notification)
        //add habit to your list of habits
        //save this list into shared preferences
        _uiState.update {
                currentState -> currentState.copy(
            habitDescription = name,
            timeofNotification = time,
            userNotification = notification
        )
        }

        _habitList.add(Habit(name, time, notification))
        habits = _habitList.toList() //TODO fix this in the future. Not proper way to do it.

        var lastHabit = if(_habitList.isEmpty()) null else _habitList.last()
        Log.d("ProjectViewModel.kt:saveHabitfromUser","LastHabit = ${lastHabit}") // just testing that our last habit gets added to list

        val gson = Gson()
        val json = gson.toJson(_habitList)
        habitz.edit().putString("_habitList", json).apply()
    }

    fun loadHabitList(context: Context) {
        val gson = Gson()
        val habitz = context.getSharedPreferences("user_habits", Context.MODE_PRIVATE)
        var json: String? = habitz.getString ("_habitList", null)

        Log.d("ProjectViewModel.kt:loadHabitList", "Loading json: ${json}")

        if(json != null) {
            val listOfMyClassObject = object : TypeToken<ArrayList<Habit>?>() {}.type
            val gson = Gson()
            val outputList: List<Habit> = gson.fromJson(json, listOfMyClassObject)
            _habitList.addAll(outputList)
            habits = _habitList.toList()
        }

        Log.d("ProjectViewModel.kt:loadHabitList", "Loaded: ${_habitList}")
    }

}


