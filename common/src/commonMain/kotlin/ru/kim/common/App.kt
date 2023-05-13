package ru.kim.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kim.common.data.SpeechRepository

@Composable
fun App() {
    val scope = rememberCoroutineScope()
    val speechRepository = SpeechRepository()
    val speechToTextState = remember { mutableStateOf("") }

    Column {
        val test = SoundRecorder()

        Button(
            content = { Text("Начать") },
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    test.start()
                }
            }
        )
        Button(
            content = { Text("Закончить") },
            onClick = {
                scope.launch {
                    test.finish()
                }
            }
        )

        Button(
            content = { Text("Голос в текст") },
            onClick = {
                scope.launch {
                    speechToTextState.value = speechRepository.translateSpeechToText().result
                }
            }
        )

        Text(speechToTextState.value)
    }
}
