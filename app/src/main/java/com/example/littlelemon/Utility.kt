package com.example.littlelemon

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun showToast(message: String) {
    val context = LocalContext.current
    val toastState = remember { mutableStateOf<Toast?>(null) }

    toastState.value?.cancel() // Cancel previous toast if any

    val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toastState.value = toast
    toast.show()
}