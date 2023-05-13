package ru.kim.common.composable

import androidx.compose.foundation.Image
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import ru.kim.common.utils.loadImage

@Composable
fun QuestionCard() {
    Card {
        Image(
            bitmap = loadImage("https://www.animalfriends.co.uk/siteassets/media/images/article-images/cat-articles/38_afi_article1_caring-for-a-kitten-tips-for-the-first-month.png"),
            contentDescription = ""
        )
    }
}
