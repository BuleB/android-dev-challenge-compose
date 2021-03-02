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

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DogCardView(dog: Dog, onClickEvent: (() -> Unit)? = null) {
    Card(elevation = 4.dp, modifier = Modifier.padding(10.dp)) {
        DogView(dog = dog, onClickEvent)
    }
}

@Composable
fun DogView(dog: Dog, onClickEvent: (() -> Unit)? = null) {
    Column(
        modifier = Modifier.clickable {
            onClickEvent?.invoke()
        }
    ) {
        Image(
            painter = painterResource(id = dog.image),
            contentDescription = "dog_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(modifier = Modifier.padding(5.dp)) {
            Text(text = dog.name, style = MaterialTheme.typography.subtitle1)
            Row {
                Text(text = dog.sex.value, style = MaterialTheme.typography.caption)
                Text(
                    text = "* ${dog.age}",
                    modifier = Modifier.padding(start = 5.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
fun DogList(onClickEvent: ((dog: Dog) -> Unit)?) {
    val dogs = remember {
        Dogs
    }
    LazyColumn {
        items(items = dogs) { item ->
            DogCardView(dog = item) {
                Log.d(TAG, "DogList: ")
                onClickEvent?.run {
                    onClickEvent(item)
                }
            }
        }
    }
}

@Composable
fun DogDetailPage(position: Int) {
    val dog = remember(key1 = position, calculation = { Dogs[position] })
    Column {
        DogView(dog)
        Text(
            text = dog.detail,
            modifier = Modifier.padding(start = 5.dp),
            style = MaterialTheme.typography.body2
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {}, Modifier.padding(vertical = 30.dp)) {
                Text(text = "Adopt")
            }
        }
    }
}

@Preview(
    "Light Theme", widthDp = 360, heightDp = 640,
    showBackground = true
)
@Composable
fun PreviewDogCard() {
    val dog = remember {
        Dogs[0]
    }
    DogCardView(dog)
}

@Preview(
    "Light Theme", widthDp = 360, heightDp = 640,
    showBackground = true
)
@Composable
fun PreviewDogDetail() {
    DogDetailPage(0)
}

const val TAG = "Mason"
