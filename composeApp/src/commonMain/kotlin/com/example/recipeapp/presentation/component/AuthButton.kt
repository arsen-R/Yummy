package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.sign_up_label
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = title
        )
    }
}

@Preview
@Composable
fun AuthButtonPreview() {
    RecipeAppTheme {
        AuthButton(onClick = { /*TODO*/ }, title = stringResource(Res.string.sign_up_label))
    }
}
