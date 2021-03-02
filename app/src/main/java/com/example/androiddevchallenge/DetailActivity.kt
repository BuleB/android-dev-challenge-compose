/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
    Scaffold(
        topBar = {
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
                }
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            DogDetailPage(position = position)
        }
    }
}

object DetailActivityParams {
    const val POSITION = "position"
}
