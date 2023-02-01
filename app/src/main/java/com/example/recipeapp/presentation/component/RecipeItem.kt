package com.example.recipeapp.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.domain.model.RecipeResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeItem(
    recipeResult: RecipeResult?,
    modifier: Modifier = Modifier,
    onItemRecipeClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .height(170.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onItemRecipeClick
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
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
                            startY = 280f
                        )
                    )
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = recipeResult?.name
                        ?: "Homemade Chicken Shawarma With Ben Stiller And Ahmed Badr",
                    modifier = Modifier
                        .weight(2f)
                        .padding(10.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                val isChecked = remember { mutableStateOf(false) }
                IconToggleButton(
                    checked = isChecked.value,
                    onCheckedChange = {

                    },
                    modifier = modifier
                        .size(60.dp)
                        .padding(0.dp, 10.dp, 10.dp, 10.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeListItemPreview() {
    RecipeItem(null, onItemRecipeClick = {})
}