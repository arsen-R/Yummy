package com.example.recipeapp.presentation.screen.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.SignInButton
import com.example.recipeapp.presentation.component.SignInButtonFromProvider
import com.example.recipeapp.presentation.component.SignUpButton
import com.example.recipeapp.presentation.navigation.navigateToSignIn
import com.example.recipeapp.presentation.navigation.navigateToSignUp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val signInWithGoogleState = viewModel.signInWithGoogle.collectAsStateWithLifecycle().value
    //LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    //Log.d("ScreenNavStateLog", "Navigate to StartScreen")
    StartScreen(
        modifier = modifier,
        navController = navController,
        onSignInWithGoogle = viewModel::signInWithGoogle,
        signInWithGoogleState = signInWithGoogleState,
    )
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSignInWithGoogle: () -> Unit,
    signInWithGoogleState: SignInWithGoogleState
) {
    //val context = LocalContext.current
    StartScreenBody(
        modifier = modifier,
        navController = navController,
        onSignInWithGoogle = onSignInWithGoogle
    )

    when (signInWithGoogleState) {
        is SignInWithGoogleState.Idle -> {
            Unit
        }

        is SignInWithGoogleState.Loading -> {
            LoadingProgressBar()
        }

        is SignInWithGoogleState.Success -> {
        }

        is SignInWithGoogleState.Error -> {
            //Toast.makeText(context, signInWithGoogleState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun StartScreenBody(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSignInWithGoogle: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxSize()
            .paint(
                painter = painterResource(Res.drawable.sign_in_background),
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
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .weight(1f),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(Res.string.start_screen_label),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = modifier.fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignUpButton(
                        onSignUp = {
                            navController.navigateToSignUp()
                        }
                    )
                    SignInButtonFromProvider(
                        onSignIn = { onSignInWithGoogle() },
                        signInIcon = painterResource(Res.drawable.apple),
                        signInTitle = stringResource(Res.string.sign_in_with_google_label)
                    )
                    SignInButton(onSignInButton = {
                        navController.navigateToSignIn()
                    })
                }
            }
        }
    }
}

@Preview()
@Composable
fun StartScreenBodyPreview() {
    RecipeAppTheme {
        StartScreenBody(
            navController = rememberNavController(),
            onSignInWithGoogle = {}
        )
    }
}