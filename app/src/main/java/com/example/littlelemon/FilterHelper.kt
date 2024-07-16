package com.example.littlelemon

import android.util.Log

class FilterHelper {

    fun filterProducts(type: FilterType, productsList: List<MenuItems>): List<MenuItems> {
        return when (type) {
            FilterType.All -> productsList
            FilterType.Drinks -> productsList
            FilterType.Mains -> productsList.filter { it.category == "mains" }
            FilterType.Dessert -> productsList.filter { it.category == "desserts" }
            FilterType.Starters ->  productsList.filter { it.category == "starters" }
            /*FilterType.Dessert -> TODO("only products with category equal to Dessert")
            FilterType.Drinks -> TODO("only products with category equal to Drinks")
            FilterType.Food -> TODO("only products with category equal to Food")*/

            else -> { return productsList }
        }
    }


}