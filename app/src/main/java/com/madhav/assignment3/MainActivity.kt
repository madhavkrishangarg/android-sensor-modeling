package com.madhav.assignment3

import android.content.Context.SENSOR_SERVICE
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
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    private var sensorEventListener: SensorEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccelerometerApp()
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterSensorListener()
    }

    private fun unregisterSensorListener() {
        sensorManager?.unregisterListener(sensorEventListener)
    }

}

@Composable
fun PrintOrientation(azimuth: Float, pitch: Float, roll: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Azimuth: $azimuth",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Pitch: $pitch",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Roll: $roll",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment3Theme {
        PrintOrientation(0.0f, 0.0f, 0.0f)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd

        ) {
            Button(
                onClick = {
//                     val intent = Intent(context, GraphActivity::class.java)
//                     context.startActivity(intent)
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AB7FF))
            ) {
                Text(text = "View Graphs"
                ,fontSize = 18.sp)
            }
        }

    }
}

@Composable
fun AccelerometerApp() {
    val context = LocalContext.current
    val sensorManager: SensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager

    //null check for sensor
    if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
        Toast.makeText(context, "No Accelerometer found", Toast.LENGTH_SHORT).show()
        Log.e("Accelerometer", "No Accelerometer found")
        return
    }

    val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    if (accelerometer == null) {
        Toast.makeText(context, "No Accelerometer found", Toast.LENGTH_SHORT).show()
        Log.e("Accelerometer", "No Accelerometer found")
        return
    }

    val AccelerometerDB = AccelerometerDatabase.DatabaseBuilder.getInstance(context)
    val AccelerometerDao = AccelerometerDB.accelerometerDao()
    val x_angle = remember { mutableStateOf(0.0f) }
    val y_angle = remember { mutableStateOf(0.0f) }
    val z_angle = remember { mutableStateOf(0.0f) }

    val sensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val g = event.values.clone()

                val norm_Of_g =
                    Math.sqrt((g[0] * g[0] + g[1] * g[1] + g[2] * g[2]).toDouble()).toFloat()

                // Normalize the accelerometer vector
                g[0] = g[0] / norm_Of_g
                g[1] = g[1] / norm_Of_g
                g[2] = g[2] / norm_Of_g

                val x = g[0]
                val y = g[1]
                val z = g[2]

                val pitch =
                    Math.atan2(x.toDouble(), Math.sqrt((y * y + z * z).toDouble())).toFloat()
                val roll = Math.atan2(y.toDouble(), Math.sqrt((x * x + z * z).toDouble())).toFloat()
                val azimuth =
                    Math.atan2(z.toDouble(), Math.sqrt((x * x + y * y).toDouble())).toFloat()

                x_angle.value = azimuth
                y_angle.value = pitch
                z_angle.value = roll

                GlobalScope.launch(Dispatchers.IO) {
                    // Database operation to insert or update data
                    AccelerometerDao.upsert(
                        AccelerometerEntity(
                            x = azimuth, y = pitch, z = roll, timestamp = System.currentTimeMillis()
                        )
                    )
                }
            }
        }


        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            // Do nothing
        }
    }


    DisposableEffect(key1 = sensorManager, key2 = accelerometer) {
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    PrintOrientation(x_angle.value, y_angle.value, z_angle.value)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd

    ) {
        Button(
            onClick = {
                     val intent = Intent(context, Graphs::class.java)
                     context.startActivity(intent)
            },
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AB7FF))
        ) {
            Text(text = "View Graphs"
                ,fontSize = 18.sp)
        }
    }

}