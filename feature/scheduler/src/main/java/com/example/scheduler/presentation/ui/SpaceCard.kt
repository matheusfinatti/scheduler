package com.example.scheduler.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.core.R
import com.example.scheduler.domain.model.OfficeSpace
import java.util.TimeZone

@Composable
fun SpaceCard(
    officeSpace: OfficeSpace,
    modifier: Modifier,
) {
    Column(modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            elevation = 4.dp,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(officeSpace.image)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .placeholder(R.drawable.placeholder)
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = officeSpace.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun SpaceCardPreview() {
    val space = OfficeSpace(
        id = 1,
        name = "Space 1",
        image = "",
        timezone = TimeZone.getDefault(),
    )

    SpaceCard(officeSpace = space, Modifier.fillMaxWidth())
}

internal interface OnOfficeSpaceInteractor {

    fun onClickOfficeSpace(space: OfficeSpace)
}
