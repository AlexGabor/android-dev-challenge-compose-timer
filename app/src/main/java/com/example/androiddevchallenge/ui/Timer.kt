package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.component.DraggableWatchFace
import com.example.androiddevchallenge.ui.theme.purple200
import com.example.androiddevchallenge.ui.theme.purple500
import com.example.androiddevchallenge.ui.theme.purple700
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun Timer() {
    val step = with(LocalDensity.current) { 96.dp.toPx().toInt() }
    Box(Modifier.fillMaxSize()) {

        DraggableWatchFace(modifier = Modifier
            .requiredSize(600.dp)
            .watchFaceLayout(step * 2),
            color = purple700
        ) {
            getWatchFaceText()
        }

        DraggableWatchFace(modifier = Modifier
            .requiredSize(600.dp)
            .watchFaceLayout(step),
            color = purple500
        ) {
            getWatchFaceText()
        }

        DraggableWatchFace(modifier = Modifier
            .requiredSize(600.dp)
            .watchFaceLayout(),
            color = purple200
        ) {
            getWatchFaceText()
        }

        Button(modifier = Modifier
            .align(Alignment.BottomCenter)
            .navigationBarsPadding()
            .padding(bottom = 16.dp), onClick = { /*TODO*/ }) {
            Text(text = "Start")
        }
    }
}

@Composable
private fun getWatchFaceText() {
    val items = (1..12).toList()
    items.forEachIndexed { index, it ->
        BasicText("$it", modifier = Modifier.rotate(360f / items.size * index), style = TextStyle(fontFamily = FontFamily(Font(R.font.recursive_regular)), fontSize = 30.sp))
    }
}

private fun Modifier.watchFaceLayout(offset: Int = 0) = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, placeable.height) {
        placeable.place(constraints.maxWidth / 2 - placeable.width / 2, constraints.maxHeight - offset)
    }
}