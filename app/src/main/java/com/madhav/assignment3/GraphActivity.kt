package com.madhav.assignment3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.DrawScope

class GraphActivity {
//
//    @Composable
//    fun LineChart(data: List<Pair<Long, Float>>, title: String) {
//        Canvas(
//            modifier = Modifier.fillMaxSize(),
//            onDraw = {
//                drawLineChart(data, title, this)
//            }
//        )
//    }
//
//    private fun drawLineChart(data: List<Pair<Long, Float>>, title: String, drawScope: DrawScope) {
//        val canvasWidth = drawScope.size.width
//        val canvasHeight = drawScope.size.height
//
//        if (data.isEmpty()) return  // Return if there is no data to draw
//
//        // Calculate data points
//        val maxTimestamp = data.maxByOrNull { it.first }?.first ?: 0L
//        val minTimestamp = data.minByOrNull { it.first }?.first ?: 0L
//        val maxX = maxTimestamp - minTimestamp
//        val maxY = data.maxByOrNull { it.second }?.second ?: 0f
//
//        val points = data.map { point ->
//            val x = ((point.first - minTimestamp) / maxX.toFloat()) * canvasWidth
//            val y = ((maxY - point.second) / maxY) * canvasHeight
//            Offset(x, y)
//        }
//
//        // Draw axes
//        drawScope.drawLine(
//            color = Color.Black,
//            start = Offset(0f, 0f),
//            end = Offset(0f, canvasHeight),
//            strokeWidth = 2f
//        )
//        drawScope.drawLine(
//            color = Color.Black,
//            start = Offset(0f, canvasHeight),
//            end = Offset(canvasWidth, canvasHeight),
//            strokeWidth = 2f
//        )
//
//        // Draw line chart
//        drawScope.drawPoints(
//            points = points.toTypedArray(),
//            pointMode = PointMode.Polyline,
//            color = Color.Blue,
//            strokeWidth = 3f
//        )
//
//        // Draw title
//        drawScope.nativeCanvas.drawText(
//            text = title,
//            x = canvasWidth / 2,
//            y = 30f,
//            paint = android.graphics.Paint().apply {
//                color = android.graphics.Color.BLACK
//                textSize = 20f
//                textAlign = android.graphics.Paint.Align.CENTER
//            }
//        )
//    }

}
