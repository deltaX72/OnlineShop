package com.deltax72.onlineshop.screens.account.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deltax72.onlineshop.R
import com.deltax72.onlineshop.data.entities.Favourites
import com.deltax72.onlineshop.screens.registration.states.AuthUserState
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun AccountViewDisplay(
    authUserState: AuthUserState,
    favourites: List<Favourites>,
    onFavouritesClicked: () -> Unit,
    onLogoutClicked: () -> Unit
) {
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 10.dp)
                ) {
                    Text(
                        text = "Личный кабинет",
                        style = MaterialTheme.styles.title1,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colors.LightGrey
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_account
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "${authUserState.firstName} ${authUserState.lastName}",
                                style = MaterialTheme.styles.title2,
                                color = MaterialTheme.colors.Black,
                                modifier = Modifier
                            )
                            Text(
                                text = authUserState.phoneNumber,
                                style = MaterialTheme.styles.caption,
                                color = MaterialTheme.colors.Grey,
                                modifier = Modifier
                            )
                        }
                    }
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.icon_logout
                        ),
                        tint = MaterialTheme.colors.Black,
                        contentDescription = null
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.LightGrey
                            )
                            .clickable {
                                onFavouritesClicked()
                            }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.icon_favourite_deactive
                                ),
                                tint = MaterialTheme.colors.Pink,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Избранное",
                                    style = MaterialTheme.styles.title2,
                                    color = MaterialTheme.colors.Black,
                                    modifier = Modifier
                                )
                                Text(
                                    text = "${favourites.size} товаров",
                                    style = MaterialTheme.styles.caption,
                                    color = MaterialTheme.colors.Grey,
                                    modifier = Modifier
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            tint = MaterialTheme.colors.Black,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.LightGrey
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.icon_shop
                                ),
                                tint = MaterialTheme.colors.Pink,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Магазины",
                                    style = MaterialTheme.styles.title2,
                                    color = MaterialTheme.colors.Black,
                                    modifier = Modifier
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.LightGrey
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.icon_feedback
                                ),
                                tint = MaterialTheme.colors.Orange,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Обратная связь",
                                    style = MaterialTheme.styles.title2,
                                    color = MaterialTheme.colors.Black,
                                    modifier = Modifier
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.LightGrey
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.icon_offer
                                ),
                                tint = MaterialTheme.colors.Grey,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Оферта",
                                    style = MaterialTheme.styles.title2,
                                    color = MaterialTheme.colors.Black,
                                    modifier = Modifier
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.LightGrey
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.icon_refund
                                ),
                                tint = MaterialTheme.colors.Grey,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Text(
                                    text = "Возврат товара",
                                    style = MaterialTheme.styles.title2,
                                    color = MaterialTheme.colors.Black,
                                    modifier = Modifier
                                )
                            }
                        }
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = R.drawable.icon_right_arrow
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.LightGrey
                    )
                    .clickable {
                        onLogoutClicked()
                    }
            ) {
                Text(
                    text = "Выйти",
                    style = MaterialTheme.styles.buttonText2,
                    color = MaterialTheme.colors.Black,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun AccountViewDisplay_Preview() {
    PreviewBox {
        AccountViewDisplay(
            authUserState = AuthUserState(
                firstName = "Иван",
                lastName = "Иванов",
                phoneNumber = "+ 7 789 789 78 89"
            ),
            favourites = emptyList(),
            onFavouritesClicked = {

            },
            onLogoutClicked = {

            }
        )
    }
}