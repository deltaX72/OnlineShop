package com.deltax72.onlineshop.screens.catalog.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.deltax72.onlineshop.ui.PreviewBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.data.entities.Items
import com.deltax72.onlineshop.data.entities.Price
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductItemCard(
    item: ProductItem,
    favourites: List<Favourites>,
    bitmapImages: List<ImageBitmap>,
    onItemClicked: () -> Unit,
    onAddToFavouritesClicked: () -> Unit
) {
    val context = LocalContext.current
    val bitmapStar = BitmapFactory.decodeStream(
        context.assets.open("images/image_star.png")
    )
    
    val pagerState = rememberPagerState {
        bitmapImages.size
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .requiredWidth(168.dp)
            .padding(bottom = 7.dp)
            .background(
                color = MaterialTheme.colors.White
            )
            .clickable {
                onItemClicked()
            }
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .clickable {

                    }
            ) {
                HorizontalPager(
                    modifier = Modifier,
                    state = pagerState,
                ) {i ->
                    Image(
                        bitmap = bitmapImages[i],
                        contentDescription = null,
                        modifier = Modifier
                            .height(144.dp)
                            .width(168.dp)
                    )
                }

                var selectedAsFav = false
                favourites.forEach { i ->
                    if (i.id == item.id) {
                        selectedAsFav = true
                        return@forEach
                    }
                }
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id =
                            if (selectedAsFav) R.drawable.icon_favourite_active
                            else R.drawable.icon_favourite_deactive
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.Pink,
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onAddToFavouritesClicked()
                        }
                )
            }
            Row(
                modifier = Modifier
                    .width(32.dp)
                    .height(8.dp)
                    .background(MaterialTheme.colors.Grey)
            ) {

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = item.price.price,
                style = MaterialTheme.styles.elementText,
                color = MaterialTheme.colors.Grey,
                modifier = Modifier
                    .padding(1.dp)
            )
            Row(
                modifier = Modifier
                    .padding(1.dp)
            ) {
                Text(
                    text = "${item.price.priceWithDiscount} ${item.price.unit}",
                    style = MaterialTheme.styles.title2,
                    color = MaterialTheme.colors.Black,
                )
                Spacer(modifier = Modifier.padding(start = 8.dp))
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(CornerSize(4.dp))
                        )
                        .background(
                            color = MaterialTheme.colors.Pink
                        )
                ) {
                    Text(
                        text = "-${item.price.discount}%",
                        style = MaterialTheme.styles.elementText,
                        color = MaterialTheme.colors.White,
                        modifier = Modifier
                            .padding(vertical = 3.dp, horizontal = 6.dp)
                    )
                }
            }
            Text(
                text = item.title,
                style = MaterialTheme.styles.title3,
                color = MaterialTheme.colors.Black,
                modifier = Modifier
                    .padding(1.dp)
            )
            Text(
                text = item.subtitle,
                maxLines = 3,
                style = MaterialTheme.styles.caption,
                color = MaterialTheme.colors.DarkGrey,
                modifier = Modifier
                    .padding(1.dp)
                    .height(37.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 4.dp, start = 4.dp),
        ) {
            Image(
                bitmap = bitmapStar.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Text(
                text = item.feedback.rating.toString(),
                style = MaterialTheme.styles.elementText,
                color = MaterialTheme.colors.Orange,
                modifier = Modifier
                    .padding(vertical = 3.dp, horizontal = 2.dp)
            )
            Text(
                text = "(${item.feedback.count})",
                style = MaterialTheme.styles.elementText,
                color = MaterialTheme.colors.Grey,
            )
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomEnd = 8.dp
                        )
                    )
                    .background(
                        color = MaterialTheme.colors.Pink
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colors.White,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
fun ProductItemCard_Preview() {
    PreviewBox {
        val context = LocalContext.current
        val bitmap = BitmapFactory.decodeStream(
            context.assets.open("images/image_product.png")
        )
        ProductItemCard(
            item = ProductItem(
                title = "title",
                subtitle = "subtitle",
                price = Price(
                    price = "0",
                    discount = 0,
                    priceWithDiscount = "0",
                    unit = "unit"
                ),

            ),
            favourites = emptyList(),
            bitmapImages = listOf(bitmap.asImageBitmap()),
            onItemClicked = {

            },
            onAddToFavouritesClicked = {

            }
        )
    }
}