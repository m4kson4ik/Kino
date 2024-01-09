package com.example.applicationkino.UserInterface

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarMenu() // Пример BottomSheetScaffold
{

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarTest()
{
   // LargeTopAppBar(title = { Text("TOPAPPBAR") }, Modifier.shadow(20.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppMenu1()
{
    TopAppBar(
        title = {
                      Column(
                          Modifier.fillMaxWidth(),
                          horizontalAlignment = Alignment.CenterHorizontally
                      ) {
                          Text("Название")
                      }
    }, navigationIcon = { Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = ""
    )}, modifier = Modifier.shadow(40.dp).clip(RoundedCornerShape(20.dp))
    )
}