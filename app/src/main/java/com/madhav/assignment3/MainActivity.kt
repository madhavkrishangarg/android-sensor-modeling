package com.madhav.assignment3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madhav.assignment3.ui.theme.Assignment3Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment3Theme {
        Greeting("Android")
    }
}

@Composable
fun WeatherButtons(date: String = "2022-01-01") {
    val cityList = listOf(
        City("New Delhi", 28.6139, 77.2090),
        City("London", 51.5074, -0.1278),
        City("New York", 40.7128, -74.0060),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        cityList.forEach { city ->
            Button(
                onClick = {
                    GlobalScope.launch {
                        try {
                            Log.i("WeatherResponse", "Weather information of ${city.name}")
                        } catch (e: Exception) {
                            // Handle errors if any
                            Log.i("WeatherResponse", "Failed to get weather information")
                        }

                    }

                }, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Get Weather of ${city.name}",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
        Text(text = "Minimum Temperature", fontSize = 20.sp)
        Text(text = "Maximum Temperature", fontSize = 20.sp)
    }
}


data class City(val name: String, val latitude: Double, val longitude: Double)

@Preview
@Composable
fun PreviewWeatherButtons() {
    WeatherButtons("2022-01-01")
}
