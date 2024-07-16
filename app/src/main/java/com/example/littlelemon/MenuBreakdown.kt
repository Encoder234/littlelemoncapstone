package com.example.littlelemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun MenuBreakdown() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box() {
            Text(
                text = "ORDER FOR DELIVERY!",
                color = LittleLemonColor.black,
                fontFamily = markaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier.padding(top=15.dp, bottom = 0.dp, end = 0.dp, start = 20.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 43.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProductButton(text = "Starters")
                ProductButton(text = "Mains")
                ProductButton(text = "Desserts")
                ProductButton(text = "Drinks")
            }

        }


    }
}

@Composable
fun ProductButton(text: String) {
    Button(
        onClick = { buttonClickHandler(text) },
        shape = RoundedCornerShape(percent = 50), // 50% percent makes it circular
        colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.lightgray),
        modifier = Modifier.width(100.dp).padding(2.dp) // Adjust width as needed
    ) {
        Text(text = text, modifier = Modifier.padding(0.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MenuBreakdownPreview() {
    MenuBreakdown()
}