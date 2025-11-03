package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R
import com.example.recipeapp.ui.theme.RecipeAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuGroupItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    subtitle: @Composable (() -> Unit)? = null,
    trailingIcon: ImageVector? = ImageVector.vectorResource(R.drawable.round_arrow_forward_ios_24)
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
                    imageVector = icon,
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = modifier.size(12.dp)
                )
            }
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
fun MenuGroupItemPreview() {
    RecipeAppTheme {
        MenuGroupItem(onClick = { /*TODO*/ }, title = "Start Application")
    }
}
