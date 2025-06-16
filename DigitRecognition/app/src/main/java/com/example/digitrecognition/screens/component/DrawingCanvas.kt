package com.example.digitrecognition.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

fun DrawingCanvas(
    paths: MutableList<List<Pair<Float, Float>>>,
    currentPath: MutableList<Pair<Float, Float>>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .pointerInput(Unit) {
                coroutineScope {
                    launch {
                        detectDragGestures(
                            onDragStart = { offset ->
                                currentPath.clear()
                                currentPath.add(Pair(offset.x, offset.y))
                            },
                            onDrag = { change, _ ->
                                currentPath.add(Pair(change.position.x, change.position.y))
                            },
                            onDragEnd = {
                                paths.add(currentPath.toList())
                                currentPath.clear()
                            }
                        )
                    }
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            paths.forEach { path ->
                for (i in 1 until path.size) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(path[i - 1].first, path[i - 1].second),
                        end = Offset(path[i].first, path[i].second),
                        strokeWidth = 8f,
                        cap = StrokeCap.Round
                    )
                }
            }
            for (i in 1 until currentPath.size) {
                drawLine(
                    color = Color.Black,
                    start = Offset(currentPath[i - 1].first, currentPath[i - 1].second),
                    end = Offset(currentPath[i].first, currentPath[i].second),
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}