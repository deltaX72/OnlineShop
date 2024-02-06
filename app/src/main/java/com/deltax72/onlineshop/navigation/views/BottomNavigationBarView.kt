package com.deltax72.onlineshop.navigation.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deltax72.onlineshop.navigation.states.BottomNavigationState
import com.deltax72.onlineshop.navigation.states.NavigationBarActiveState
import com.deltax72.onlineshop.navigation.states.NavigationBarItem
import com.deltax72.onlineshop.navigation.states.NavigationBarItems
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun BottomNavigationBarView(
    state: BottomNavigationState?,
) {
    when (state?.activeState) {
        is NavigationBarActiveState.Enabled -> {
            NavigationBar(
                containerColor = MaterialTheme.colors.White,
                contentColor = MaterialTheme.colors.White,
            ) {
                state.items.items.forEach { item ->
                    val isSelected = state.selectedItem.screen == item.screen
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            item.onEvent()
                        },
                        icon = {
                            Icon(
                                contentDescription = item.title,
                                imageVector = ImageVector.vectorResource(
                                    id = item.icon!!
                                ),
                                tint =
                                    if (isSelected) MaterialTheme.colors.Pink
                                    else MaterialTheme.colors.Black,
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.styles.caption
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedTextColor = MaterialTheme.colors.Pink,
                            indicatorColor = MaterialTheme.colors.White
                        ),
                    )
                }
            }
        }
        else -> {}
    }
}

@Preview
@Composable
fun BottomNavigationBarView_Preview() {
    PreviewBox {
        BottomNavigationBarView(
            BottomNavigationState(
                activeState = NavigationBarActiveState.Enabled,
                items = NavigationBarItems.BottomMain(
                    null
                ),
                selectedItem = NavigationBarItem.Catalog(),
            ),
        )
    }
}