package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun SignInButton(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit,
    signInIcon: Painter,
    signInTitle: String
) {
    OutlinedButton(
        onClick = { onSignIn() },
        modifier = modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50f),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Icon(
            painter = signInIcon, contentDescription = null,
            modifier = modifier.size(24.dp),
            tint = Color.Unspecified
        )
        Text(
            text = signInTitle,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .weight(1f)
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
fun SignInButtonPreview() {
    RecipeAppTheme {
        SignInButton(
            onSignIn = { /*TODO*/ },
            signInIcon = painterResource(id = R.drawable.google),
            signInTitle = "Continue with Google"
        )
    }
}