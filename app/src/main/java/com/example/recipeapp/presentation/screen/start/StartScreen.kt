package com.example.recipeapp.presentation.screen.start

import android.app.Activity.RESULT_OK
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.LockScreenOrientation
import com.example.recipeapp.presentation.component.SignInButton
import com.example.recipeapp.presentation.component.SignInButtonFromProvider
import com.example.recipeapp.presentation.component.SignUpButton
import com.example.recipeapp.presentation.navigation.Screen
import com.example.recipeapp.presentation.navigation.navigateToHome
import com.example.recipeapp.presentation.navigation.navigateToSignIn
import com.example.recipeapp.presentation.navigation.navigateToSignUp
import com.example.recipeapp.ui.theme.RecipeAppTheme
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider.getCredential

@Composable
internal fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val tapSignInResponse = viewModel.onTapSignInResponse.collectAsStateWithLifecycle().value
    val signInWithGoogleState = viewModel.signInWithGoogle.collectAsStateWithLifecycle().value
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Log.d("ScreenNavStateLog", "Navigate to StartScreen")
    StartScreen(
        modifier = modifier,
        navController = navController,
        onTapSignInWithGoogle = viewModel::onTapSignInResponse,
        onSignInWithGoogle = viewModel::signInWithGoogle,
        tapSignInResultState = tapSignInResponse,
        signInWithGoogleState = signInWithGoogleState,
        onTapSignInClient = viewModel.oneTapClient
    )
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onTapSignInWithGoogle: () -> Unit,
    onSignInWithGoogle: (googleCredential: AuthCredential) -> Unit,
    tapSignInResultState: OnTapSignInResultState,
    signInWithGoogleState: SignInWithGoogleState, onTapSignInClient: SignInClient
) {
    val context = LocalContext.current
    StartScreenBody(
        modifier = modifier,
        navController = navController,
        onSignInWithGoogle = onTapSignInWithGoogle
    )
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val credentials = onTapSignInClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = getCredential(googleIdToken, null)
                    onSignInWithGoogle(googleCredentials)
                } catch (it: ApiException) {
                    print(it)
                }
            }
        }

    when (tapSignInResultState) {
        is OnTapSignInResultState.Idle -> {
            Unit
        }

        is OnTapSignInResultState.Success -> {
            tapSignInResultState.beginSignInResult?.let {
                LaunchedEffect(it) {
                    launch(it, launcher)
                }
            }
        }

        is OnTapSignInResultState.Loading -> {
            LoadingProgressBar()
        }

        is OnTapSignInResultState.Error -> {
            Toast.makeText(context, tapSignInResultState.errorMessage, Toast.LENGTH_LONG).show()

        }
    }

    when (signInWithGoogleState) {
        is SignInWithGoogleState.Idle -> {
            Unit
        }

        is SignInWithGoogleState.Loading -> {
            LoadingProgressBar()
        }

        is SignInWithGoogleState.Success -> {
            navController.navigateToHome()
        }

        is SignInWithGoogleState.Error -> {
            Toast.makeText(context, signInWithGoogleState.errorMessage, Toast.LENGTH_LONG).show()
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
                        text = stringResource(id = R.string.start_screen_label),
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
                        signInIcon = painterResource(id = R.drawable.google),
                        signInTitle = stringResource(id = R.string.sign_in_with_google_label)
                    )
                    SignInButton(onSignInButton = {
                        navController.navigateToSignIn()
                    })
                }
            }
        }
    }
}

fun launch(
    signInResult: BeginSignInResult,
    launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
) {
    val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
    launcher.launch(intent)
}


@Preview(
    name = "Light Mode Portrait",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Preview(
    name = "Dark Mode Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Preview(
    name = "Light Mode Landscape",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"

)
@Preview(
    name = "Dark Mode Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun StartScreenBodyPreview() {
    RecipeAppTheme {
        StartScreenBody(
            navController = rememberNavController(),
            onSignInWithGoogle = {}
        )
    }
}