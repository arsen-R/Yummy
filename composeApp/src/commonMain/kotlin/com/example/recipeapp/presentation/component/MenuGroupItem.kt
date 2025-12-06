package com.example.recipeapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.resources.Res
import com.example.recipeapp.resources.round_arrow_forward_ios_24
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuGroupItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    subtitle: @Composable (() -> Unit)? = null,
    trailingIcon: Painter? = painterResource(Res.drawable.round_arrow_forward_ios_24)
) {
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
            trailingIcon?.let { icon ->
                Icon(
                    painter = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = modifier.size(12.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun MenuGroupItemPreview() {
    RecipeAppTheme {
        MenuGroupItem(onClick = { /*TODO*/ }, title = "Start Application")
    }
}
