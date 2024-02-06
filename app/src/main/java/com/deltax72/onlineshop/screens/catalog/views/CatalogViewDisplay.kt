package com.deltax72.onlineshop.screens.catalog.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deltax72.onlineshop.R
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.screens.catalog.states.CollapsableSection
import com.deltax72.onlineshop.screens.catalog.states.SortType
import com.deltax72.onlineshop.screens.catalog.states.SortedItems
import com.deltax72.onlineshop.screens.catalog.states.Tag
import com.deltax72.onlineshop.screens.catalog.states.Tag.Companion.copy
import com.deltax72.onlineshop.screens.catalog.states.TagsList
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun CatalogViewDisplay(
    items: SortedItems,
    favourites: List<Favourites>,
    onByPopularitySelected: () -> Unit,
    onByPriceReductionSelected: () -> Unit,
    onByPriceIncreaseSelected: () -> Unit,
    onCarouselItemSelected: (Tag) -> Unit,
    onItemClicked: (ProductItem, List<ImageBitmap>) -> Unit,
    onAddToFavouritesClicked: (ProductItem) -> Unit
) {
    val context = LocalContext.current
    val images = listOf(
        BitmapFactory.decodeStream(context.assets.open("images/image_product_1.png")).asImageBitmap(),
        BitmapFactory.decodeStream(context.assets.open("images/image_product_2.png")).asImageBitmap(),
        BitmapFactory.decodeStream(context.assets.open("images/image_product_3.png")).asImageBitmap(),
        BitmapFactory.decodeStream(context.assets.open("images/image_product_4.png")).asImageBitmap(),
        BitmapFactory.decodeStream(context.assets.open("images/image_product_5.png")).asImageBitmap(),
        BitmapFactory.decodeStream(context.assets.open("images/image_product_6.png")).asImageBitmap(),
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.White
            )
            .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 10.dp)
            ) {
                Text(
                    text = "Каталог",
                    style = MaterialTheme.styles.title1,
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Box {
                    CollapsableLazyColumn(
                        sections = listOf(
                            CollapsableSection(
                                title = items.sortType.type,
                                rows = mapOf(
                                    SortType.ByPopularity.type to {
                                        onByPopularitySelected()
                                    },
                                    SortType.ByPriceReduction.type to {
                                        onByPriceReductionSelected()
                                    },
                                    SortType.ByPriceIncrease.type to {
                                        onByPriceIncreaseSelected()
                                    },
                                )
                            )
                        )
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.icon_filter
                        ),
                        contentDescription = null
                    )
                    Text(
                        text = "Фильтры",
                        style = MaterialTheme.styles.title4,
                        color = MaterialTheme.colors.DarkGrey,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {

                            }
                    )
                }
            }

            LazyRow(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val listWithTags = emptyList<Tag>().toMutableList()

                with (TagsList().tags) {
                    forEach { tag ->
                        if (tag.name == items.tag.name) {
                            listWithTags += tag.copy(true)
                        }
                    }
                    forEach { tag ->
                        if (tag.name != items.tag.name) {
                            listWithTags += tag.copy(false)
                        }
                    }
                }

                listWithTags.forEach { tag ->
                    item {
                        CarouselItem(
                            text = tag.name,
                            isSelected = tag.isSelected,
                            onCloseClicked = {

                            },
                            onItemSelected = {
                                onCarouselItemSelected(tag)
                            }
                        )
                    }
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(168.dp),
            ) {
                for (i in 0 until items.items.items.size) {
                    item {
                        val bitmapImages = when (items.items.items[i].id) {
                            "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(
                                images[5],
                                images[4]
                            )

                            "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(
                                images[0],
                                images[1]
                            )

                            "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(
                                images[4],
                                images[5]
                            )

                            "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(
                                images[2],
                                images[3]
                            )

                            "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(
                                images[1],
                                images[2]
                            )

                            "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                                images[5],
                                images[0]
                            )

                            "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                                images[3],
                                images[2]
                            )

                            "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                                images[0],
                                images[4]
                            )

                            else -> throw NoSuchElementException()
                        }
                        ProductItemCard(
                            item = items.items.items[i],
                            favourites = favourites,
                            bitmapImages = bitmapImages,
                            onItemClicked = {
                                onItemClicked(
                                    items.items.items[i],
                                    bitmapImages
                                )
                            },
                            onAddToFavouritesClicked = {
                                onAddToFavouritesClicked(items.items.items[i])
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CatalogViewDisplay_Preview() {
    PreviewBox {
        CatalogViewDisplay(
            items = SortedItems(),
            favourites = emptyList(),
            onByPopularitySelected = {},
            onByPriceReductionSelected = {},
            onByPriceIncreaseSelected = {},
            onCarouselItemSelected = { tag ->

            },
            onItemClicked = { item, images ->

            },
            onAddToFavouritesClicked = { item ->

            }
        )
    }
}