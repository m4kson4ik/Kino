package com.example.applicationkino.UserInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.applicationkino.Model.Random.Doc
import com.example.applicationkino.Model.Random.Random
import com.example.applicationkino.ui.theme.GreenHighLight
import com.example.applicationkino.ui.theme.GreenLite
import com.example.applicationkino.ui.theme.Purple40
import com.example.applicationkino.ui.theme.Purple80
import com.example.applicationkino.ui.theme.PurpleGrey40

data class Afisha(
    val id : Int,
    val name : String,
    val names : List<Names>,
    val type : String,
    val typeNumber : Int,
    val year : Int,
    val description : String,
    val slogan : String,
    val status : String,
    val votes : Rating
)
data class Names(
    val name : String,
    val language : String,
    val type : String,
)

data class Rating(
    val kp : Float,
    val imdb : Float,
    val rmb : Float,
    val filmCritics : Int,
    val russianFilmCrtitics : Float,
)


@Composable
fun AfishaSection(list : List<Doc?>)
{
    LazyRow()
    {
        items(list)
        {
            if (it != null)
            {
                AfishaCard(item = it)
            }
        }
    }
}

@Composable
fun AfishaCard(item : Doc)
{
    Box(Modifier.padding(16.dp))
    {
        Column(
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Gradient(Purple40, Purple80))
                .width(161.dp)
                .height(241.dp).padding(10.dp)) {
            Text(item.alternativeName)
            Text(item.type)
            Text(item.year.toString())
        }
    }
}

fun Gradient(start : Color, end : Color) : Brush
{
    return Brush.horizontalGradient(listOf(start, end))
}

@Composable
fun SmallIconHistory()
{
    LazyRow()
    {
        items(10)
        {
            SmallCard()
        }
    }
}

@Composable
fun SmallCard()
{
    Box(Modifier.padding(10.dp))
    {
        Column(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Gradient(GreenLite, GreenHighLight))
                .width(75.dp)
                .height(75.dp)
        ) {

        }
    }
}