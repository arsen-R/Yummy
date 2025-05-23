package com.example.recipeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.navigation.navigateToRecipeDetail

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeItem(
    recipeResult: Recipe?,
    modifier: Modifier = Modifier,
    navController: NavController,
    isRecipeSaved: Boolean,
    onSavedRecipeClick: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .size(170.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {
            navController.navigateToRecipeDetail(recipeResult?.id!!)
        }
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipeResult?.thumbnail_url)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Recipe Image",
                modifier = modifier.fillMaxSize(),
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 225f
                        )
                    )
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = recipeResult?.name!!,
                    modifier = Modifier
                        .weight(2f)
                        .padding(10.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                IconToggleButton(
                    checked = isRecipeSaved,
                    onCheckedChange = onSavedRecipeClick,
                    modifier = modifier
                        .size(60.dp)
                        .padding(0.dp, 10.dp, 10.dp, 10.dp),
                ) {
                    Icon(
                        imageVector = if (isRecipeSaved) {
                            ImageVector.vectorResource(R.drawable.round_bookmark_24)
                        } else {
                            ImageVector.vectorResource(R.drawable.round_bookmark_border_24)
                        },
                        contentDescription = null,
                        modifier = modifier.size(25.dp),
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeListItemPreview() {
    val recipeResult = Recipe(
        name = "Homemade Chicken Shawarma With Ben Stiller And Ahmed Badr",
    )
    RecipeItem(
        recipeResult = recipeResult,
        navController = rememberNavController(),
        onSavedRecipeClick = {},
        isRecipeSaved = false
    )
}