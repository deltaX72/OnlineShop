package com.deltax72.onlineshop.screens.catalog.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deltax72.onlineshop.R
import com.deltax72.onlineshop.screens.catalog.states.CollapsableSection
import com.deltax72.onlineshop.ui.PreviewBox
import com.deltax72.onlineshop.ui.theme.colors
import com.deltax72.onlineshop.ui.theme.styles

@Composable
fun CollapsableLazyColumn(
    sections: List<CollapsableSection>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) {
        sections
            .map { true }
            .toMutableStateList()
    }
    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            collapsedState[i] = !collapsed
                        }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.icon_sort_by
                        ),
                        contentDescription = null
                    )
                    Text(
                        text = dataItem.title,
                        style = MaterialTheme.styles.title4,
                        color = MaterialTheme.colors.DarkGrey,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (collapsed) R.drawable.icon_down_arrow
                            else R.drawable.icon_right_arrow
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colors.Black,
                    )
                }
            }
            if (!collapsed) {
                dataItem.rows.forEach { i ->
                    item {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    i.value()
                                }
                        ) {
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = i.key,
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun CollapsableLazyColumn() {
    PreviewBox {
        CollapsableLazyColumn(
            sections = listOf(
                CollapsableSection(
                    title = "По популярности",
                    rows = mapOf(
                        "По популярности" to {

                        },
                        "По убыванию цены" to {

                        },
                        "По возрастанию цены" to {

                        }
                    )
                )
            )
        )
    }
}