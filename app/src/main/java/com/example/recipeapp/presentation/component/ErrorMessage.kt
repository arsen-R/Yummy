package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

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
            modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Button(
            onClick = onRetryClick,
            modifier = modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.round_refresh_24),
                contentDescription = "Refresh Icon",
                tint = MaterialTheme.colors.onPrimary
                )
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = "Try Again",
                textAlign = TextAlign.End,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview(showBackground = true, name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode",
)
@Composable
fun ErrorMessagePreview() {
    RecipeAppTheme {
        ErrorMessage(message = "No Connection", onRetryClick = {})
    }
}