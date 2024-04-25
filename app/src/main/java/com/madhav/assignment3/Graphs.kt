package com.madhav.assignment3

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.madhav.assignment3.ui.theme.Assignment3Theme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import android.Manifest
import android.app.Activity
import android.provider.MediaStore
import androidx.activity.result.IntentSenderRequest


class Graphs : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphsActivity()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GraphsActivity()
}

class GraphsViewModel(application: Application) : AndroidViewModel(application) {
    private val accelerometerDao =
        AccelerometerDatabase.DatabaseBuilder.getInstance(application).accelerometerDao()

    val accelerometerData by lazy {
        accelerometerDao.getAccelerometerData().asLiveData(viewModelScope.coroutineContext)
    }
}



@Composable
fun GraphsActivity(graphsViewModel: GraphsViewModel = viewModel()) {
    val context = LocalContext.current
    val accelerometerData by graphsViewModel.accelerometerData.observeAsState(initial = emptyList())


    Log.i("GraphsActivity", "Accelerometer data: $accelerometerData")

    if (accelerometerData.isNotEmpty()) {
        Log.i("GraphsActivity", "Accelerometer data: $accelerometerData")
        LineGraph(accelerometerData)

    } else {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "No accelerometer data available")
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd
    ) {
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .width(140.dp)
                .height(100.dp)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6AB7FF))
        ) {
            Text(
                text = "Back", fontSize = 18.sp
            )
        }
    }


}

@Composable
fun LineGraph(accelerometerData: List<AccelerometerEntity>) {

}

