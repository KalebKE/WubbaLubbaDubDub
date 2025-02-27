package com.kalebkircher.wubbalubbadubdub.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kalebkircher.wubbalubbadubdub.R

class PagingSelectionGrid(@StringRes val loadingMessage: Int, @StringRes val errorMessage: Int, @DrawableRes val placeHolderIcon: Int) {

    @Composable
    fun SelectionItemLazyVerticalGrid(items: LazyPagingItems<SelectionGridItem>, onSelected: (Long) -> Unit, modifier: Modifier = Modifier) {

        when (items.loadState.refresh) {
            is LoadState.Error -> {
                Error(errorMessage)
            }

            is LoadState.Loading -> {
                Loading(loadingMessage)
            }

            else -> {
                LazyVerticalGrid(
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    columns = GridCells.Adaptive(minSize = 150.dp), // Adjust minSize for desired column width
                    contentPadding = PaddingValues(8.dp) // Add padding around the grid
                ) {
                    items(
                        count = items.itemCount,
                        key = items.itemKey { it.id },
                    ) { index ->
                        items[index]?.let {
                            SelectionItem(it, onSelected = onSelected, modifier)
                        }
                    }
                }

            }
        }


    }

    @Composable
    fun SelectionItem(
        item: SelectionGridItem, onSelected: (Long) -> Unit, modifier: Modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onSelected(item.id) }, verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(placeHolderIcon),
                contentDescription = stringResource(R.string.content_description_character_icon, item.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(150.dp)
                    .height(150.dp)
            )

            Text(modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp), text = item.title, style = MaterialTheme.typography.titleMedium)
        }
    }
}

data class SelectionGridItem(val id: Long, val title: String, val imageUrl: String)