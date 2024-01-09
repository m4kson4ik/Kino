package com.example.applicationkino.UserInterface

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.example.applicationkino.ModelSearch.Doc
import com.example.applicationkino.TypeKinoItem
import com.example.applicationkino.ViewModel.ViewModelPoster
import com.example.applicationkino.ui.theme.ButtonDontSee1
import com.example.applicationkino.ui.theme.ButtonDontSee2
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PosterSection(
    list: List<Doc>?,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModelPoster: ViewModelPoster,
    navController: NavController
)
{
    LazyRow()
    {
        if (list != null)
        {
            items(items = list)
            {
                PosterCard(doc = it, lifecycleCoroutineScope, viewModelPoster, navController )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition", "SuspiciousIndentation")
@Composable
fun PosterCard(
    doc: Doc,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModelPoster: ViewModelPoster,
    navController: NavController
)
{
    Box(Modifier.padding(16.dp))
    {
        Column(
            Modifier
                .width(161.dp)
                .height(241.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    lifecycleCoroutineScope.launch {
                        viewModelPoster.selectedKino.emit(doc)
                        navController.navigate("selected")
                    }
                }
        ) {
            val bitmap = BitmapFactory.decodeByteArray(doc.poster.byte, 0, doc.poster.byte!!.size)
            if (bitmap != null)
            {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "",
                )
            }
        }
    }
}

@Composable
fun YouNotSee()
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .width(334.dp)
        .height(37.dp), contentAlignment = Alignment.TopCenter)
    {
        Column(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .width(334.dp)
                .height(37.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Gradient(ButtonDontSee1, ButtonDontSee2))
                .clickable { }) {
            Button(onClick = {
            }, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Text("Не знаете что посмотреть?", color = Color.Black, fontStyle = FontStyle.Normal)
            }
        }
    }
}

//@SuppressLint("CoroutineCreationDuringComposition")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AlterDialogShow(viewModelPoster: ViewModelPoster)
//{
//    viewModelPoster.getConnection()
//    var showDialog by remember { mutableStateOf(false) }
//    var scope = rememberCoroutineScope()
//
//    val errorX by viewModelPoster.errorEx.collectAsState()
//
//    var error = when (errorX)
//    {
//        ErrorList.OK202 -> false
//        ErrorList.ERROR -> true
//        ErrorList.WAIT -> true
//        ErrorList.LOADINGROOM -> false
//    }
//
//    Text(error.toString())
//
//    if (error)
//    {
//        AlertDialog(
//            tonalElevation = 20.dp,
//            icon = { Icon(imageVector = Icons.Filled.Error, contentDescription = "")},
//            title = {
//                    Column {
//                        Icon(painter = painterResource(id = R.drawable.errorimage), contentDescription = "")
//                        Text("Произошла ошибка")
//                    }
//            },
//            text = {
//                Text(errorX.toString())
//            },
//            onDismissRequest = {
//                error = false
//            },
//            confirmButton = {
//                Button(onClick = {
//                    viewModelPoster.getConnection()
//                }) {
//                    Text("Повторить")
//                }
//            })
//    }
//}


@Composable
fun TextInLeft(text : String)
{
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { })
    {
        Column {
            Text(text, color = Color.White, fontStyle = FontStyle.Normal)
        }
    }
}

@Composable
fun ChipsController(items : List<TypeKinoItem>?)
{
    LazyRow()
    {
        if (items != null) {
            items(items = items)
            {
                Box(Modifier.padding(10.dp))
                {
                    FilterChipItem(it.name)
                }
            }
        }
    }
}

@Composable
fun AssistChipsItem(label : String)
{
    AssistChip(label = {Text(label)} , onClick = { })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipItem(label: String)
{
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    var colorText by remember {
        mutableStateOf(Color.White)
    }
    FilterChip(
        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = ButtonDontSee1) ,
        selected = selected,
        onClick = {
            selected = !selected
            colorText = if (selected)
            {
                 Color.Black
            } else Color.White
                  },
        label = { Text(text = label, color = colorText) },
        leadingIcon = if (selected) {
            {
                Icon(imageVector = Icons.Filled.Done, contentDescription = null)
            }
        } else null
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search()
{
    var search by remember {
        mutableStateOf("")
    }
    var isShowSearch by remember {
        mutableStateOf(false)
    }
    SearchBar(shape = RoundedCornerShape(20.dp), query = search, onQueryChange = { search = it }, onSearch = { }, active = isShowSearch, onActiveChange = { isShowSearch = it} ) {
        Text("ad")
    }
}