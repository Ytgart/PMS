package ru.kim.common.data

import com.beust.klaxon.Klaxon
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.cio.*
import org.apache.http.HttpHeaders
import ru.kim.common.domain.SpeechToTextResponse
import java.io.File

class SpeechRepository {

    private val httpClient = HttpClient()

    suspend fun translateSpeechToText(): SpeechToTextResponse {
        val response = httpClient.post("https://stt.api.cloud.yandex.net/speech/v1/stt:recognize") {
            headers {
                append(
                    HttpHeaders.AUTHORIZATION,
                    "Bearer t1.9euelZqJyonJmJnGzY3LiZGOlomexu3rnpWancbJl5Ocx8vNnprOmJHOlM_l9PduRQFd-e9YWX_v3fT3LnR-XPnvWFl_7w.pKDKxxWXio6jti6-v8YJfuBKbNybCsnFV-7usPyzwW_cUiKCs0ny7Kx9hYUo7AWPd6dGYK0ZHJGTeseksnWxCg"
                )
                append(
                    HttpHeaders.CONTENT_TYPE,
                    "audio/ogg"
                )
            }
            url {
                parameters.append("folderId", "b1gbq0g26j6lpoj66h9h")
            }
            setBody(
                File("C:\\Test\\RecordAudio.ogg").readChannel()
            )
        }.bodyAsText()
        return Klaxon().parse<SpeechToTextResponse>(response) ?: SpeechToTextResponse()
    }
}