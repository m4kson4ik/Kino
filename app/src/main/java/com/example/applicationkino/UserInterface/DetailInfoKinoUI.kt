package com.example.applicationkino.UserInterface

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.applicationkino.ViewModel.ViewModelPoster
import com.example.applicationkino.ui.theme.DefaultColorMainForm
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailInfoKino(viewModel: ViewModelPoster)
{
    val person by viewModel.stateFlowPeronItem.collectAsState()
    val item by viewModel.selectedKino.collectAsState()
    viewModel.getPersonItem(item?.id.toString())
    Log.d("mgkit", person?.docs.toString())
    Scaffold(topBar = { TopAppMenu1() }) { pad ->
        Column(Modifier.fillMaxSize().background(DefaultColorMainForm).padding(pad)) {
            Text(item?.description.toString())
        }
    }
}