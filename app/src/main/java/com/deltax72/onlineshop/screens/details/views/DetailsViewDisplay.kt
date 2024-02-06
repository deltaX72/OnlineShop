package com.deltax72.onlineshop.screens.details.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.deltax72.onlineshop.data.entities.Feedback
import com.deltax72.onlineshop.data.entities.Price
import com.deltax72.onlineshop.data.entities.ProductItem
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsViewDisplay(
    bitmapImages: List<ImageBitmap>,
    item: ProductItem,
    favourites: List<Favourites>,
    onAddToFavouritesClicked: (ProductItem) -> Unit,
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current
    val imageStar = BitmapFactory.decodeStream(
        context.assets.open("images/image_star.png")
    )

    val pagerState = rememberPagerState {
        bitmapImages.size
    }
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colors.White
            )
            .padding(16.dp)
            .verticalScroll(
                state = scrollState,
                enabled = true
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
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
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.icon_send,
                    ),
                    contentDescription = null
                )
            }

            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .padding(top = 16.dp)
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
                            .height(368.dp)
                            .fillMaxWidth()
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
                    tint = MaterialTheme.colors.Pink,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onAddToFavouritesClicked(item)
                        }
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(32.dp)
                    .height(8.dp)
                    .background(MaterialTheme.colors.Grey)
            ) {

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.styles.title1,
                    color = MaterialTheme.colors.Grey,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.styles.largeTitle,
                    color = MaterialTheme.colors.Black,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = "Доступно для заказа ${item.available} штук",
                    style = MaterialTheme.styles.text1,
                    color = MaterialTheme.colors.Grey,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    for (i in 0 until 5) {
                        Image(
                            bitmap = imageStar.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 10.dp, height = 12.dp)
                        )
                    }
                    Text(
                        text = item.feedback.rating.toString(),
                        style = MaterialTheme.styles.text1,
                        color = MaterialTheme.colors.Grey,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.ellipse
                        ),
                        tint = MaterialTheme.colors.Grey,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(6.dp)
                            .size(2.dp)
                    )
                    Text(
                        text = "${item.feedback.count} отзывов",
                        style = MaterialTheme.styles.text1,
                        color = MaterialTheme.colors.Grey,
                        modifier = Modifier
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${item.price.priceWithDiscount} ${item.price.unit}",
                        style = MaterialTheme.styles.title2,
                        color = MaterialTheme.colors.Black,
                    )
                    Text(
                        text = item.price.price,
                        style = MaterialTheme.styles.text1,
                        color = MaterialTheme.colors.Grey,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    )
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
                    text = "Описание",
                    style = MaterialTheme.styles.title1,
                    color = MaterialTheme.colors.Black,
                    modifier = Modifier
                        .padding(top = 24.dp)
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colors.LightGrey,
                        contentColor = MaterialTheme.colors.Black
                    ),
                    onClick = {

                    }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.styles.title2,
                            color = MaterialTheme.colors.Black,
                            modifier = Modifier
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = item.description,
                    style = MaterialTheme.styles.text1,
                    color = MaterialTheme.colors.DarkGrey,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Скрыть",
                    style = MaterialTheme.styles.buttonText1,
                    color = MaterialTheme.colors.Grey,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {

                        }
                )
                Text(
                    text = "Характеристики",
                    style = MaterialTheme.styles.title1,
                    color = MaterialTheme.colors.Black,
                    modifier = Modifier
                        .padding(top = 34.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item.info.forEach { i ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp, bottom = 4.dp)
                        ) {
                            Text(
                                text = i.title,
                                style = MaterialTheme.styles.text1,
                                color = MaterialTheme.colors.DarkGrey,
                                modifier = Modifier
                            )
                            Text(
                                text = i.value,
                                style = MaterialTheme.styles.text1,
                                color = MaterialTheme.colors.DarkGrey,
                                modifier = Modifier
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 34.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Состав",
                        style = MaterialTheme.styles.title1,
                        color = MaterialTheme.colors.Black,
                        modifier = Modifier
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.icon_copy
                        ),
                        contentDescription = null
                    )
                }

                Text(
                    text = item.ingredients,
                    style = MaterialTheme.styles.text1,
                    color = MaterialTheme.colors.DarkGrey,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Подробнее",
                    style = MaterialTheme.styles.buttonText1,
                    color = MaterialTheme.colors.Grey,
                    maxLines = 2,
                    modifier = Modifier
                        .clickable {

                        }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .clip(
                            shape = RoundedCornerShape(CornerSize(8.dp))
                        )
                        .background(
                            color = MaterialTheme.colors.Pink
                        )
                        .clickable {

                        }
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${item.price.priceWithDiscount} ${item.price.unit}",
                                style = MaterialTheme.styles.buttonText2,
                                color = MaterialTheme.colors.White,
                            )
                            Text(
                                text = item.price.price,
                                style = MaterialTheme.styles.caption,
                                color = MaterialTheme.colors.LightPink,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                            )
                        }
                        Text(
                            text = "Добавить в корзину",
                            style = MaterialTheme.styles.buttonText2,
                            color = MaterialTheme.colors.White,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun DetailsViewDisplay_Preview() {
    PreviewBox {
        val context = LocalContext.current
        val images = listOf(
            BitmapFactory.decodeStream(context.assets.open("images/image_product_1.png")).asImageBitmap(),
            BitmapFactory.decodeStream(context.assets.open("images/image_product_2.png")).asImageBitmap(),
            BitmapFactory.decodeStream(context.assets.open("images/image_product_3.png")).asImageBitmap(),
            BitmapFactory.decodeStream(context.assets.open("images/image_product_4.png")).asImageBitmap(),
            BitmapFactory.decodeStream(context.assets.open("images/image_product_5.png")).asImageBitmap(),
            BitmapFactory.decodeStream(context.assets.open("images/image_product_6.png")).asImageBitmap(),
        )

        DetailsViewDisplay(
            bitmapImages = images,
            favourites = emptyList(),
            item = ProductItem(
                title = "title",
                subtitle = "subtitle",
                price = Price(
                    price = "899",
                    discount = 39,
                    priceWithDiscount = "549",
                    unit = "Р"
                ),
                feedback = Feedback(
                    count = "0",
                    rating = 0.0
                ),
                description = "gweugrnijn mgivjroeni eniegnr" +
                        "eijgnrugnr ognrog nrognreog" +
                        "nroner grjegrj egoergj oujre nreuj" +
                        "gnregnr eignbr eignbre igbneirg" +
                        "ribe gnbire" +
                        "fdhht jhnjono hyuy ohh bvgyuv ygbugv ug"
            ),
            onAddToFavouritesClicked = { item ->

            },
            onBackClicked = {

            }
        )
    }
}