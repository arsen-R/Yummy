package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.email_label
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EmailOutlinedTextField(
    modifier: Modifier = Modifier,
    userEmailText: String,
    onSetUserEmail: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        value = userEmailText,
        onValueChange = {
            onSetUserEmail(it)
        },
        label = {
            Text(
                text = stringResource(Res.string.email_label),
                color = MaterialTheme.colors.onSurface
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
        ),
        textStyle = TextStyle(color = MaterialTheme.colors.onPrimary, fontSize = 18.sp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            cursorColor = MaterialTheme.colors.onSurface,
        ),
        singleLine = true,
    )
}

@Preview
@Composable
fun EmailOutlinedTextFieldPreview() {
    RecipeAppTheme {
        EmailOutlinedTextField(
            userEmailText = "a",
            onSetUserEmail = {}
        )
    }
}