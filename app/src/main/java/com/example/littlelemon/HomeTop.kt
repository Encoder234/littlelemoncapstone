package com.example.littlelemon


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeTop(navController: NavHostController) {
    Row( modifier = Modifier.fillMaxWidth().padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween ) {

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Little Lemon Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth(0.5F).padding(horizontal = 20.dp, vertical = 0.dp).align(Alignment.CenterVertically)

        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth(0.50F).padding(horizontal = 20.dp, vertical = 0.dp)
                .clickable {
                    // Navigate to Page2.kt when Image 2 is clicked
                    navController.navigate("Profile")
                }
            )
    }
} //Header

@Preview(showBackground = true)
@Composable
fun HeaderTopPreview() {
    //HeaderTop();
}