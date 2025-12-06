package com.example.recipeapp.presentation.screen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.component.AuthButton
import com.example.recipeapp.presentation.component.EmailOutlinedTextField
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.PasswordOutlinedTextField
import com.example.recipeapp.presentation.component.TopAppBarWithArrow
import com.example.recipeapp.resources.*
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignInViewModel = koinViewModel<SignInViewModel>()
) {
    val signInState = viewModel.signInState.collectAsStateWithLifecycle().value
    //LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    SignInScreen(
        modifier = modifier,
        navController = navController,
        onFetchSignIn = viewModel::fetchSignIn,
        signInState = signInState
    )
}

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onFetchSignIn: (email: String, password: String) -> Unit,
    signInState: SignInState
) {
    Scaffold(
        topBar = {
            TopAppBarWithArrow(
                modifier = modifier,
                navController = navController
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                SignInBody(
                    modifier = modifier,
                    onFetchSignIn = onFetchSignIn,
                )
            }
        }
    )
    SignIn(
        modifier = modifier,
        signInState = signInState,
        navController = navController
    )
}

@Composable
fun SignInBody(
    modifier: Modifier = Modifier,
    onFetchSignIn: (email: String, password: String) -> Unit,
) {
    val emailTextField = rememberSaveable {
        mutableStateOf(Constants.EMPTY_STRING)
    }
    val passwordTextField = rememberSaveable {
        mutableStateOf(Constants.EMPTY_STRING)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(.5f)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.Bottom
            )
        ) {
            Image(
                painter = painterResource(Res.drawable.apple),
                contentDescription = "app logo",
                modifier = modifier.padding(top = 15.dp)
            )
            Text(
                text = stringResource(Res.string.login_account_label),
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colors.onBackground
                )
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailOutlinedTextField(
                modifier = modifier,
                userEmailText = emailTextField.value,
                onSetUserEmail = { newValue ->
                    emailTextField.value = newValue
                }
            )
            PasswordOutlinedTextField(
                modifier = modifier,
                userPasswordText = passwordTextField.value,
                onSetUserPassword = { newValue ->
                    passwordTextField.value = newValue
                }
            )
            AuthButton(
                modifier = modifier,
                onClick = {
                    onFetchSignIn(emailTextField.value, passwordTextField.value)
                },
                title = stringResource(Res.string.sign_in_label)
            )
            Text(
                text = stringResource(Res.string.sign_in_terms_of_service_label),
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    signInState: SignInState,
    navController: NavController
) {
    //val context = LocalContext.current

    when (signInState) {
        is SignInState.Idle -> {
            Unit
        }

        is SignInState.Loading -> {
            LoadingProgressBar(modifier = modifier)
        }

        is SignInState.Success -> {
            //navController.navigateToHome()
        }

        is SignInState.Error -> {
//            Toast.makeText(
//                context,
//                "Error: ${signInState.error.message}",
//                Toast.LENGTH_LONG
//            ).show()
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Composable
fun SignInScreenPreview() {
    RecipeAppTheme {
        SignInBody(
            onFetchSignIn = { email, password -> },
        )
    }
}