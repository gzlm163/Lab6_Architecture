package com.example.artspaceapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize()) {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp(viewModel: ArtSpaceViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val isWideScreen = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isWideScreen) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                ArtworkImage(uiState.currentIndex)
            }
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArtworkDetails(uiState.currentIndex)
                Spacer(modifier = Modifier.height(24.dp))
                ArtworkButtons(
                    onPrev = { viewModel.previousImage() },
                    onNext = { viewModel.nextImage() }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                ArtworkImage(uiState.currentIndex)
            }
            Spacer(modifier = Modifier.height(24.dp))
            ArtworkDetails(uiState.currentIndex)
            Spacer(modifier = Modifier.height(24.dp))
            ArtworkButtons(
                onPrev = { viewModel.previousImage() },
                onNext = { viewModel.nextImage() }
            )
        }
    }
}

@Composable
fun ArtworkImage(index: Int) {
    val imageRes = when (index) {
        0 -> R.drawable.image1
        1 -> R.drawable.image2
        2 -> R.drawable.image3
        else -> R.drawable.image1
    }
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun ArtworkDetails(index: Int) {
    val title = when (index) {
        0 -> "In the Forest in Autumn"
        1 -> "The Horsewoman"
        2 -> "The Scream"
        else -> "Unknown Artwork"
    }
    val artist = when (index) {
        0 -> "Isaac Levitan (1895)"
        1 -> "Karl Bryullov (1832)"
        2 -> "Edvard Munch (1893)"
        else -> "Unknown Artist"
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(text = artist, fontSize = 16.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun ArtworkButtons(onPrev: () -> Unit, onNext: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = onPrev,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onNext,
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray, contentColor = Color.Black)
        ) {
            Text("Next")
        }
    }
}