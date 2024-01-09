package com.example.applicationkino.UserInterface

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DownloadCircular()
{
    var isLoading by remember {
        mutableStateOf(false)
    }
        Column {
            Button(onClick = { isLoading = !isLoading }) {
                if (isLoading)
                {
                    Text("stop")
                }
                else
                {
                    Text("START")
                }
            }
            if (isLoading)
            {
                CircularProgressIndicator()
            }
        }
}