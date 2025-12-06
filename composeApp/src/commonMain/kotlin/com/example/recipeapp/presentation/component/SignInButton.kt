package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.sign_in_label
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SignInButton(
    modifier: Modifier = Modifier,
    onSignInButton: () -> Unit
) {
    TextButton(
        onClick = onSignInButton,
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50f),
    ) {
        Text(
            text = stringResource(Res.string.sign_in_label),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun SignInButtonPreview() {
    RecipeAppTheme {
        SignInButton(onSignInButton = {})
    }
}