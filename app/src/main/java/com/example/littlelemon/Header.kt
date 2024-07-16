package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.Fit,
            //modifier = Modifier.fillMaxWidth(0.5F).padding(horizontal = 20.dp, vertical = 20.dp)
            modifier = Modifier.fillMaxWidth().statusBarsPadding().height(105.dp).padding(top = 30.dp, bottom = 30.dp)
        )
    }
} //Header

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header();
}