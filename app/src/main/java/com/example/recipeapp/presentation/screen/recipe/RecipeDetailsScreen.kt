package com.example.recipeapp.presentation.screen.recipe

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.IngredientItemListHeader
import com.example.recipeapp.presentation.component.LoadingProgressBar

@Composable
internal fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipeId: Int,
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    viewModel.fetchRecipeDetail(recipeId)
    val recipeDetail = viewModel.uiState.collectAsStateWithLifecycle().value

    when {
        recipeDetail.isLoading -> {
            LoadingProgressBar()
        }
        recipeDetail.recipe != null -> {
            //RecipeDetailScreen(navController = navController, recipe = recipeDetail.recipe)
        }
        recipeDetail.throwable != null -> {
            ErrorMessage(
                message = stringResource(id = R.string.no_connection),
                onRetryClick = {
                    viewModel.fetchRecipeDetail(recipeId)
                }
            )
        }
    }
    Log.d("List Recipe Response", "Detail Screen = $recipeDetail")
}

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    recipe: Recipe? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = recipe?.name ?:"Homemade Chicken Shawarma With Ben Stiller And Ahmed Badr",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = recipe?.description ?: ("Shawarma is a Middle Eastern dish that’s become a " +
                    "popular street food around the world, but it’s easy enough to " +
                    "make in your own kitchen. This recipe calls for boneless skinless " +
                    "chicken thighs that are marinated in a fragrant mixture of spices. " +
                    "Bake the chicken in the oven, then toss it into pita bread with cucumber, " +
                    "tomatoes, pickles, and a homemade white sauce made with Greek yogurt and " +
                    "lemon juice."),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            lineHeight = 18.sp
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe?.thumbnail_url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = "Recipe Image"
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Ingredients for",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(text = "1 servings")
            }
            Column(Modifier.weight(1f)) {
                Text(text = "1")
            }
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            recipe?.instructions?.forEach {
                IngredientItemListHeader()
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RecipeDetailScreenPreview() {
    val navController = rememberNavController()
    RecipeDetailScreen(navController = navController)
}
