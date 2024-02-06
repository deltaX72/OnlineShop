package com.deltax72.onlineshop.screens.catalog.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deltax72.onlineshop.R
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun CarouselItem(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onItemSelected: () -> Unit,
    onCloseClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(CornerSize(100.dp))
            )
            .background(
                color = if (isSelected) MaterialTheme.colors.DarkBlue
                else MaterialTheme.colors.LightGrey
            )
    ) {
        if (isSelected) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 12.dp,
                        end = 8.dp
                    )
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.styles.title4,
                    color = MaterialTheme.colors.White
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.icon_small_close
                    ),
                    tint = MaterialTheme.colors.White,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onCloseClicked()
                        }
                        .size(20.dp)
                )
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.styles.buttonText2,
                    color = MaterialTheme.colors.Grey,
                    modifier = Modifier
                        .clickable {
                            onItemSelected()
                        }
                )
            }
        }
    }
}

@Composable
@Preview
fun CarouselItem_Preview() {
    PreviewBox {
        CarouselItem(
            text = "Смотреть все",
            isSelected = true,
            onItemSelected = {},
            onCloseClicked = {}
        )
    }
}