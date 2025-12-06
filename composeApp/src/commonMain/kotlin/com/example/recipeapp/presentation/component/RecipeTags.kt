package com.example.recipeapp.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.Tag
import com.example.recipeapp.ui.theme.RecipeAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun RecipeTagsPreview() {
    RecipeAppTheme {
        //RecipeTags()
    }
}
