package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    onSignUp: () -> Unit
) {
    OutlinedButton(
        onClick = { onSignUp() },
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50f),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_label),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SignUpButtonPreview() {
    RecipeAppTheme {
        SignUpButton(
            onSignUp = { /*TODO*/ }
        )
    }
}