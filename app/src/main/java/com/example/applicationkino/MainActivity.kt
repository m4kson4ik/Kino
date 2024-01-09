package com.example.applicationkino

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationkino.Person.Doc
import com.example.applicationkino.UserInterface.ChipsController
import com.example.applicationkino.UserInterface.DetailInfoKino
import com.example.applicationkino.UserInterface.PosterSection
import com.example.applicationkino.UserInterface.TextInLeft
import com.example.applicationkino.UserInterface.TopAppMenu1
import com.example.applicationkino.UserInterface.YouNotSee
import com.example.applicationkino.ViewModel.ViewModelPoster
import com.example.applicationkino.ui.theme.ApplicationKinoTheme
import com.example.applicationkino.ui.theme.DefaultColorMainForm
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()
{

}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : ViewModelPoster by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationKinoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "mainForm" )
                    {
                        listNav(navController, viewModel, lifecycleScope)
                    }
                }
                }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.listNav(
    navController: NavController,
    viewModelPoster: ViewModelPoster,
    lifecycleCoroutineScope: LifecycleCoroutineScope
)
{
    composable("mainForm")
    {
        ContentMainForm(pad = PaddingValues(10.dp), viewModelPoster, lifecycleCoroutineScope, navController)
    }
    composable("selected")
    {
        DetailInfoKino(viewModelPoster)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentMainForm(pad : PaddingValues, viewModel : ViewModelPoster, lifecycleCoroutineScope: LifecycleCoroutineScope, navController: NavController)
{
    Column(
        Modifier
            .fillMaxSize()
            .background(DefaultColorMainForm)
            .padding(pad)
            .verticalScroll(rememberScrollState())
    )
    {
        val item by viewModel.stateFlowPoster.collectAsState()
        val itemPopular by viewModel.stateFlowPosterPopular.collectAsState()
        val type by viewModel.stateFlowTypeKino.collectAsState()
        val ticketsKino by viewModel.stateFlowTickets.collectAsState()
        Log.d("mgkit", type.toString())
        PosterSection(item.second?.docs, lifecycleCoroutineScope, viewModel, navController)
        YouNotSee()
        TextInLeft("Категории")
        ChipsController(type)
        TextInLeft("Сейчас в кино")
        PosterSection(list = ticketsKino.second?.docs, lifecycleCoroutineScope = lifecycleCoroutineScope, viewModelPoster = viewModel, navController = navController)
        TextInLeft("Популярное")
        PosterSection(list = itemPopular.second?.docs, lifecycleCoroutineScope, viewModel, navController)
        //AlterDialogShow(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar()
{
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetPeekHeight = 50.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column {
                LazyColumn()
                {
                    items(10)
                    {
                        Text(text = "ITEM")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }) { pad ->

    }
}



@Composable
fun ItemsPersons(list : List<Doc>)
{
    LazyColumn {
        items (10)
        {
            Box()
            {
                Column {
                    Text(text = "dsa")
                }  
            }
        }
    }
}

@Preview
@Composable
fun CardPerson()
{
    Box(modifier = Modifier
        .width(75.dp)
        .height(75.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color.White)
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "ds")
        }
    }
}


