package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.recipeapp.ui.theme.RecipeAppTheme

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

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true

)
@Composable
fun MenuGroupItemWithSwitchPreview() {
    RecipeAppTheme {
        MenuGroupItemWithSwitch(onClick = { /*TODO*/ }, title = "Start Application")
    }
}