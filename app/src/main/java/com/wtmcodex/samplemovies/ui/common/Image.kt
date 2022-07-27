package com.wtmcodex.samplemovies.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.wtmcodex.samplemovies.R


@Composable
fun AsyncImage(
    url: String?,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    @DrawableRes placeholderDrawable: Int = R.drawable.ic_place_holder,
    contentScale: ContentScale = ContentScale.Crop,
) {
    Image(
        contentScale = contentScale,
        painter = rememberImagePainter(
            data = url,
            builder = {
                placeholder(placeholderDrawable)
                crossfade(true)
            }
        ),
        contentDescription = contentDescription,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    AsyncImage("")
}