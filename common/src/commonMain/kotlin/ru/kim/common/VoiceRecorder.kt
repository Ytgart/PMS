package ru.kim.common

import java.io.File
import java.io.IOException
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.TargetDataLine

class SoundRecorder {

    private var wavFile = File("C:/Test/RecordAudio.wav")
    private var fileType = AudioFileFormat.Type.WAVE
    private var line: TargetDataLine? = null
    private val audioFormat: AudioFormat
        get() {
            val sampleRate = 48000f
            val sampleSizeInBits = 16
            val channels = 2
            val signed = true
            val bigEndian = false
            return AudioFormat(
                sampleRate, sampleSizeInBits,
                channels, signed, bigEndian
            )
        }

    fun start() {
        try {
            val format = audioFormat
            val info = DataLine.Info(TargetDataLine::class.java, format)

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                println("Line not supported")
            }
            line = AudioSystem.getLine(info) as TargetDataLine
            line?.apply {
                open(format)
                start() // start capturing
            }
            val ais = AudioInputStream(line)

            AudioSystem.write(ais, fileType, wavFile)
        } catch (ex: LineUnavailableException) {
            ex.printStackTrace()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    fun finish() {
        line?.apply {
            stop()
            close()
            println("Finished")
        }
    }
}
