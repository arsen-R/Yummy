package com.example.recipeapp.presentation.screen.signup

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipeapp.R
import com.example.recipeapp.domain.util.Constants
import com.example.recipeapp.presentation.component.AuthButton
import com.example.recipeapp.presentation.component.EmailOutlinedTextField
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.LockScreenOrientation
import com.example.recipeapp.presentation.component.PasswordOutlinedTextField
import com.example.recipeapp.presentation.component.TopAppBarWithArrow
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
internal fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState = viewModel.signUpState.collectAsStateWithLifecycle().value
    val saveUserState = viewModel.saveUserState.collectAsStateWithLifecycle().value
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    SignUpScreen(
        modifier = modifier,
        navController = navController,
        onSignUpNewUser = viewModel::fetchSignUpNewUserByEmailAndPassword,
        signUpState = signUpState,
        saveUserState = saveUserState,
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onSignUpNewUser: (email: String, password: String) -> Unit,
    signUpState: SignUpState,
    saveUserState: SignUpState,
) {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBarWithArrow(
            modifier = modifier,
            navController = navController
        )
    }, content = {
        Box(modifier = modifier.padding(it)) {
            SignUpBody(
                modifier = modifier,
                onSignUpNewUser = onSignUpNewUser,
            )
        }
    })
    SignUp(
        modifier = modifier,
        signUpState = signUpState,
        navController = navController
    )
}

@Composable
fun SignUpBody(
    modifier: Modifier = Modifier,
    onSignUpNewUser: (email: String, password: String) -> Unit,
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
                painter = painterResource(id = R.drawable.google),
                contentDescription = "app logo",
                modifier = modifier.padding(top = 15.dp)
            )
            Text(
                text = stringResource(id = R.string.create_account_label),
                modifier = modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 1.dp
            )
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
                    onSignUpNewUser(emailTextField.value, passwordTextField.value)
                },
                title = stringResource(id = R.string.sign_up_label)
            )
            Text(
                text = stringResource(id = R.string.sign_up_terms_of_service_label),
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
fun SignUp(
    modifier: Modifier = Modifier,
    signUpState: SignUpState,
    navController: NavController
) {
    val context = LocalContext.current
    when (signUpState) {
        is SignUpState.Idle -> {
            Unit
        }

        is SignUpState.Loading -> {
            LoadingProgressBar(modifier = modifier)
        }

        is SignUpState.Success -> {
        }

        is SignUpState.Error -> {
            Toast.makeText(
                context,
                "Error: ${signUpState.error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
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
fun SignUpScreenPreview() {
    RecipeAppTheme {
        SignUpBody(
            onSignUpNewUser = { email, password -> },
            )
    }
}