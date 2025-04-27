package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun SearchAppBar(
    query: String,
    onTextChanged: (String) -> Unit,
    onSearchRecipe: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, color = Color.Gray, RoundedCornerShape(5.dp)),
            value = query,
            onValueChange = {
                onTextChanged(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_screen)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSearchRecipe(query)
                    keyboardController?.hide()
                },
            ),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.round_search_24),
                    contentDescription = "Search Icon"
                )
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onPrimary, fontSize = 18.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary,
                cursorColor = MaterialTheme.colors.onPrimary,
            ),
            singleLine = true,
        )
    }
}

@Preview(name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun SearchAppBarPreview() {
    RecipeAppTheme {
        SearchAppBar(query = "Pizza", onTextChanged = {

        }, onSearchRecipe = {})
    }
}