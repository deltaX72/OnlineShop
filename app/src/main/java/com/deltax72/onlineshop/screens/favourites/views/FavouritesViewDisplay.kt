package com.deltax72.onlineshop.screens.favourites.views

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.deltax72.onlineshop.screens.catalog.views.ProductItemCard
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun FavouritesViewDisplay(
    favourites: List<Favourites>,
    onAddToFavouritesClicked: (ProductItem) -> Unit,
    onBackClicked: () -> Unit,
    onItemClicked: (ProductItem, List<ImageBitmap>) -> Unit,
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
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.icon_left_arrow,
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onBackClicked()
                        }
                )
                Text(
                    text = "Избранное",
                    style = MaterialTheme.styles.title1,
                    color = MaterialTheme.colors.Black,
                    modifier = Modifier
                        .padding(start = 28.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .padding(horizontal = 16.dp),

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(CornerSize(8.dp))
                        )
                        .background(
                            color = MaterialTheme.colors.White
                        )
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Товары",
                        style = MaterialTheme.styles.buttonText2,
                        color = MaterialTheme.colors.Black,
                        modifier = Modifier
                            .padding(vertical = 3.dp, horizontal = 6.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(CornerSize(8.dp))
                        )
                        .background(
                            color = MaterialTheme.colors.White
                        )
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Бренды",
                        style = MaterialTheme.styles.buttonText2,
                        color = MaterialTheme.colors.Grey,
                        modifier = Modifier
                            .padding(vertical = 3.dp, horizontal = 6.dp)
                    )
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(168.dp),
                modifier = Modifier
            ) {
                for (i in favourites.indices) {
                    item {
                        val bitmapImages = when (favourites[i].id) {
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
                            item = favourites[i].item,
                            favourites = favourites,
                            bitmapImages = bitmapImages,
                            onItemClicked = {
                                onItemClicked(favourites[i].item, bitmapImages)
                            },
                            onAddToFavouritesClicked = {
                                onAddToFavouritesClicked(favourites[i].item)
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
fun FavouritesViewDisplay_Preview() {
    PreviewBox {
        FavouritesViewDisplay(
            favourites = emptyList(),
            onAddToFavouritesClicked = {

            },
            onBackClicked = {

            },
            onItemClicked = { item, images ->

            }
        )
    }
}