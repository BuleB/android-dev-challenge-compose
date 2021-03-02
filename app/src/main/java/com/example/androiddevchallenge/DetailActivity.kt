package com.example.androiddevchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = intent.getIntExtra(DetailActivityParams.POSITION, 0)
        val onBack: () -> Unit = {
            finish()
        }
        setContent {
            DetailPage(position, onBack)
        }

    }
}

@Composable
fun DetailPage(position: Int, onBack: () -> Unit) {
    val dog = remember(key1 = position, calculation = { Dogs[position] })
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = dog.name) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    Modifier.clickable {
                        onBack()
                    }
                )
            })
    }) {
        Surface(color = MaterialTheme.colors.background) {
            DogDetailPage(position = position)
        }
    }
}

object DetailActivityParams {
    const val POSITION = "position"
}