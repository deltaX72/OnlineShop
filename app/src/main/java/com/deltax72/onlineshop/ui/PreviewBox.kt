package com.deltax72.onlineshop.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deltax72.onlineshop.ui.theme.OnlineShopTheme
import com.deltax72.onlineshop.ui.theme.colors

@Composable
fun PreviewBox(content: @Composable () -> Unit) {
    OnlineShopTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.White
        ) {
            content()
        }
    }
}