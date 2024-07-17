package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonColor



@Composable
fun UpperDeck(  search: (String) -> Unit ) {



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = LittleLemonColor.green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp), // Minimal space between items
        horizontalAlignment = Alignment.Start
    ) {
        /*Text(
            text = stringResource(id = R.string.title),
            fontFamily = markaFontFamily,
            fontSize = 70.sp,
            //fontWeight = FontWeight.Bold,
            color = LittleLemonColor.yellow,
            style = TextStyle(lineHeight = 0.sp),
            modifier = Modifier.padding(0.dp)

        )

        Text(
            text = stringResource(id = R.string.location),
            fontFamily = markaFontFamily,
            fontSize = 50.sp,
            color = LittleLemonColor.cloud,
            modifier = Modifier.padding(0.dp)
        ) */

        Box(
        ) {
            Text(
                text = stringResource(id = R.string.title),
                fontFamily = markaFontFamily,
                fontSize = 70.sp,
                color = LittleLemonColor.yellow,
                modifier = Modifier.padding(top = 0.dp, start = 0.dp)
            )
            Text(
                text = stringResource(id = R.string.location),
                fontFamily = markaFontFamily,
                fontSize = 50.sp,
                color = LittleLemonColor.cloud,
                modifier = Modifier.padding(top = 60.dp, start = 0.dp)
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    //style = MaterialTheme.typography.body1,
                    color = LittleLemonColor.cloud,
                    fontFamily = karlaFontFamily,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(bottom = 0.dp, end = 20.dp)
                        .fillMaxWidth(0.6f)
                )
                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier.clip(RoundedCornerShape(10.dp))
                )
            }


        }// Box

        //-----------------------------

        val searchQuery = rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Enter search phrase", color = Color.Black) },
            leadingIcon = {
                IconButton(onClick = { /* Handle search action */
                    search(searchQuery.value)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Enter search phrase",
                        tint = Color.Black
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.LightGray,
                textColor = Color.Black,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.DarkGray,
                cursorColor = Color.Black
            ),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )

        /*Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow)
        ) {
            Text(
                text = stringResource(id = R.string.order_button_text),
            )
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun UpperPanelDeck() {
    //UpperDeck()
}
