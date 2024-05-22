package com.example.recipeapp.presentation.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.SignInButton
import com.example.recipeapp.presentation.component.SignUpButton
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    SignInScreen(modifier = modifier)
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.sign_in_background),
                contentScale = ContentScale.Crop
            )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 1f
                    )
                )
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.start_screen_label),
                        color = Color.White,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier.padding(bottom = 45.dp)
                    )
                }
                SignInButton(
                    onSignIn = { /*TODO*/ },
                    signInIcon = painterResource(id = R.drawable.google),
                    signInTitle = stringResource(id = R.string.sign_in_with_google_label)
                )
                SignInButton(
                    onSignIn = { /*TODO*/ },
                    signInIcon = painterResource(id = R.drawable.facebook),
                    signInTitle = stringResource(id = R.string.sign_in_with_facebook_label)
                )
                SignInButton(
                    onSignIn = { /*TODO*/ },
                    signInIcon = painterResource(id = R.drawable.apple),
                    signInTitle = stringResource(id = R.string.sign_in_with_apple_label)
                )
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    RecipeAppTheme {
        SignInScreen()
    }
}