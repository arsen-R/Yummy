package com.example.recipeapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuGroupItemWithSwitch(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    subtitle: @Composable (() -> Unit)? = null
) {
    val checked = remember {
        mutableStateOf(false)
    }
    ListItem(
        modifier = modifier.clickable { onClick() },
        text = {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colors.onBackground,
                    fontSize = 16.sp
                )
            )
        },
        secondaryText = subtitle,
        singleLineSecondaryText = true,
        trailing = {
            Switch(
                checked = checked.value,
                onCheckedChange = {
                    checked.value = it
                }
            )
        }
    )
}

@Preview
@Composable
fun MenuGroupItemWithSwitchPreview() {
    RecipeAppTheme {
        MenuGroupItemWithSwitch(onClick = { /*TODO*/ }, title = "Start Application")
    }
}