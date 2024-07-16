package com.example.littlelemon

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.LittleLemonColor



@Composable
fun Menu(dishes: List<MenuItems>) {


    Column {
        /*LazyColumn {
            for (item in globalMenu) {

                DisplayMenu(item)
                Log.d("-- data --", item.title)
            }

            itemsIndexed(dishes) { _, MenuItems ->
                DisplayMenu(MenuItems)
                Log.d("-- displayAll --", MenuItems.title)
            }
        }*/

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(dishes) { index, item ->

                DisplayMenu(item)
                /*Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    BasicText(text = "Item #$index: Name: ${item.title}, Age: ${item.price}")
                }*/
            }
        }


    }
}

/*data class Mitem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)*/

@SuppressLint("CheckResult")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayMenu(mitem: MenuItems) {

    Card(onClick = {
        Log.d("AAA", "Click ${mitem.id}")
    }) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            Column {
                Text(text = mitem.title, style = MaterialTheme.typography.h2)
                Text(
                    text = mitem.description,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp))
                Text(text = "$${mitem.price}", style = MaterialTheme.typography.body2)
            }

            //val context = LocalContext.current

            AndroidView(
                factory = { context ->
                    ImageView(context).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) { imageView ->
                Glide.with(imageView)
                    .load(mitem.image)
                    .into(imageView)
            }


            /*Image(
                painter = painterResource(id = mitem.imageResource),
                contentDescription = "Dish image",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))) */


        } //Row
    } // Card
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp, color =  LittleLemonColor.lightgray

    )
}