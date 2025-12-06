package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

//@Composable
//fun LockScreenOrientation(orientation: Int) {
//    val context = LocalContext.current
//    DisposableEffect(orientation) {
//        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
//        val originalOrientation = activity.requestedOrientation
//        // lock screen orientation
//        activity.requestedOrientation = orientation
//        onDispose {
//            // restore original orientation when view disappears
//            activity.requestedOrientation = originalOrientation
//        }
//    }
//}
