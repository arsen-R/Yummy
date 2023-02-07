package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(modifier: Modifier = Modifier, message: String, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Button(
            onClick = onRetryClick,
            modifier = modifier
                .padding(top = 5.dp).align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onPrimary)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh Icon",
                )
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = "Try Again",
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(message = "No Connection", onRetryClick = {})
}