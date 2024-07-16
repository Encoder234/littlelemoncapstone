package com.example.littlelemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    // add code here
    @SerialName("menu")
    val menu: List<MenuItemNetwork>,
)

@Serializable
data class MenuItemNetwork(
    // add code here
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String,

    ) {
    fun toMenuItemRoom() = MenuItems(
        // add code here
        id,
        title,
        description,
        price,
        image,
        category

    )
}
