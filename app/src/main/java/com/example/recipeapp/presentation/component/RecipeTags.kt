package com.example.recipeapp.presentation.component

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.domain.model.Tag
import com.example.recipeapp.ui.theme.RecipeAppTheme

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun RecipeTags(
    modifier: Modifier = Modifier,
    tags: List<Tag>,
//    titleTag: String,
//    filterTag: String
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        FlowRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            tags.forEach { tag ->
                Chip(onClick = {
                    Toast.makeText(context, tag.name, Toast.LENGTH_LONG).show()
                }, modifier = modifier.padding(horizontal = 5.dp)) {
                    Text(text = tag.display_name!!)
                }
            }

//            Chip(onClick = { /*TODO*/ }, modifier = modifier.padding(horizontal = 5.dp)) {
//                Text(text = "Easy")
//            }
//            Chip(onClick = { /*TODO*/ }, modifier = modifier.padding(horizontal = 5.dp)) {
//                Text(text = "Under 1 Hour")
//            }
//            Chip(onClick = { /*TODO*/ }, modifier = modifier.padding(horizontal = 5.dp)) {
//                Text(text = "Under 15 Minutes")
//            }
//            Chip(onClick = { /*TODO*/ }, modifier = modifier.padding(horizontal = 5.dp)) {
//                Text(text = "Under 30 Minutes")
//            }
//            Chip(onClick = { /*TODO*/ }, modifier = modifier.padding(horizontal = 5.dp)) {
//                Text(text = "Under 45 Minutes")
//            }
        }
    }
}

@Preview(name = "Light mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark mode"
)
@Composable
fun RecipeTagsPreview() {
    RecipeAppTheme {
        //RecipeTags()
    }
}
