package com.jokyxray.bingdailywallpaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jokyxray.bingdailywallpaper.model.DailyImage
import com.jokyxray.bingdailywallpaper.ui.theme.BingDailyWallpaperTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BingDailyWallpaperTheme {
                // A surface container using the 'background' color from the theme
                MainPage()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainPage() {
    BingDailyWallpaperTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar() {
                    Text(text = "Daily Wallpaper")
                }
            }
        ) {
            Greeting("Android")
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Greeting(name: String) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
        repeat(12) {
            item {
                ImageItem()
            }
        }
    }
}

@Composable
fun ImageItem() {
    Row(modifier = Modifier
        .clickable {
            //TODO click
        }
        .fillMaxWidth()
        .aspectRatio(1.7F)
    ) {
        Text(text = "啊飒飒大大")
        CoilImage(
            data = "https://developer.android.com/images/brand/Android_Robot.png",
            contentDescription = "Android Log",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainPage()
}