package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.ui.theme.PictonBlue
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MenuGroup(
    modifier: Modifier = Modifier,
    subTitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier.padding(top = 10.dp).fillMaxWidth()) {
        subTitle?.let { subtitle ->
            Text(
                text = subtitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = PictonBlue,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = modifier.padding(start = 15.dp)
            )
        }
        Column(modifier = modifier, content = content)
    }
}

@Preview
@Composable
fun MenuGroupPreview() {
    RecipeAppTheme {
        MenuGroup(subTitle = "Settings") {
            MenuGroupItem(onClick = { /*TODO*/ }, title = "Start Application")
            MenuGroupItem(onClick = { /*TODO*/ }, title = "Start Application", trailingIcon = null)
        }
    }
}