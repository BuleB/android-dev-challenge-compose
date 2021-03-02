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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.theme.MyTheme


class MainActivity : AppCompatActivity() {
    private val viewModel: DogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }
        viewModel.detailDog.observe(this) {
            if (it != -1) {
                startActivity(Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivityParams.POSITION, it)
                })
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(viewModel: DogViewModel? = null) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Dogs") })
    }) {
        Surface(color = MaterialTheme.colors.background) {
            DogList {
                val position = Dogs.indexOf(it)
                viewModel?.navigationToDetailPage(position = position)
            }
        }
    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}


class DogViewModel : ViewModel() {

    private val _detailDog = MutableLiveData(-1)
    val detailDog: LiveData<Int> = _detailDog

    fun navigationToDetailPage(position: Int) {
        _detailDog.value = position
    }

}

data class Dog(
    val name: String,
    val age: String,
    val sex: Gender,
    @DrawableRes val image: Int,
    val detail: String
)

enum class Gender(val value: String) {
    FEMALE("female"),
    MALE("male")
}

val Dogs = listOf(
    Dog(
        "Devi",
        "2 months",
        Gender.MALE,
        R.drawable.devi_dogs,
        "At DogTime, our mission is to keep dogs out of shelters and get them adopted to good homes by providing novice and experienced owners alike with the important information needed to make them, and their dogs, very happy and healthy."
    ),
    Dog(
        "Bella",
        "6 months",
        Gender.FEMALE,
        R.drawable.walking_the_dog_day,
        "DogTime covers all things dog. Our extensive pure- and mixed-breed profiles include the history and evaluation of various dog breed characteristics--such as behaviors, shedding, adaptability--and what they mean for a potential owner."
    ), Dog(
        "Molly",
        "12 months",
        Gender.FEMALE,
        R.drawable.gum_disease_dogs,
        "For those looking to take the plunge and adopt a four-legged friend, take the matchup quiz and questionnaire to find the pooch who best fits your lifestyle and interests. You can even check local shelters in your zip code using our adoption page. And don't forget the extensive lists of dog names by breed, theme, and other categories."
    )
)