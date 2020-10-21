package com.example.testapplication.ui.file

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.*


class FileViewModel : ViewModel() {

    private val _isCreatedFile = MutableLiveData<Boolean>()
    val isCreatedFile: LiveData<Boolean> = _isCreatedFile

    private val _isWroteText = MutableLiveData<Boolean>()
    val isWroteText: LiveData<Boolean> = _isWroteText

    private val fileName: String = "file.txt"

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private var gpxFile: File? = null

    private val _path = MutableLiveData<String>()
    val path: LiveData<String> = _path

    fun createFile(context: FragmentActivity) {

        try {
            gpxFile = File(context.filesDir, fileName)
            _path.value = gpxFile!!.absoluteFile.toString()
            _isCreatedFile.value = true
        } catch (e: IOException) {
            _isCreatedFile.value = false
            e.printStackTrace()
        }

    }

    fun writeTextToFile(text: String) {

        if (text.isBlank()) {
            _isWroteText.value = false
            return
        }

        if (gpxFile != null) {
            val writer = FileWriter(gpxFile)
            writer.append(text)
            writer.flush()
            writer.close()
            _isWroteText.value = true
        } else {
            _isCreatedFile.value = false
        }

    }

    fun readTextFromFile(context: Context?) {
        val bufferedReader = BufferedReader(InputStreamReader(context?.openFileInput(fileName)))
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            _text.value = line
        }
        bufferedReader.close()

    }

}