package com.example.recipeapp.presentation.screen.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
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
import coil.transform.RoundedCornersTransformation
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.Nutrition
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.presentation.component.DetailTopBar
import com.example.recipeapp.presentation.component.ErrorMessage
import com.example.recipeapp.presentation.component.IngredientItemListHeader
import com.example.recipeapp.presentation.component.InstructionListItem
import com.example.recipeapp.presentation.component.LoadingProgressBar
import com.example.recipeapp.presentation.component.NutritionInfoBody
import com.example.recipeapp.presentation.component.RecipeItem

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val recipeDetail = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            DetailTopBar(navController = navController)
        },
        content = {
            when {
                recipeDetail.isLoading -> {
                    LoadingProgressBar()
                }

                recipeDetail.recipe != null -> {
                    RecipeDetail(
                        navController = navController,
                        recipe = recipeDetail.recipe,
                        similarRecipe = recipeDetail.similarRecipe,
                        modifier = modifier.padding(it),
                        onRecipeSaved = viewModel::isRecipeAdded,
                        onSaveRecipe = viewModel::insertRecipe,
                        onRemoveRecipe = viewModel::removeRecipe
                    )
                }

                recipeDetail.throwable != null -> {
                    ErrorMessage(
                        message = stringResource(id = R.string.no_connection),
                        onRetryClick = {
                            viewModel.fetchRecipeDetails()
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun RecipeDetail(
    modifier: Modifier = Modifier,
    navController: NavController,
    recipe: Recipe? = null,
    similarRecipe: RecipeList? = null,
    onRecipeSaved: (recipeId: Int) -> Boolean,
    onSaveRecipe: (recipe: Recipe) -> Unit,
    onRemoveRecipe: (recipeId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                text = recipe?.name!!,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                fontFamily = FontFamily.SansSerif,
            )
            recipe.description?.let {
                Text(
                    text = it,
                    modifier = modifier
                        .fillMaxWidth(),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = 18.sp
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.thumbnail_url)
                    .crossfade(true)
                    .transformations(RoundedCornersTransformation(25f))
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Recipe Image",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.ingredient_for_label),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.serving,
                            count = recipe.num_servings!!,
                            recipe.num_servings
                        )
                    )
                }
            }

        }
        items(recipe?.sections ?: emptyList()) {
            IngredientItemListHeader(ingredient = it)
        }
        item {
            recipe?.nutrition?.let { nutrition ->
                NutritionInfoBody(nutrition = nutrition)
            }
        }
        item {
            similarRecipe?.results?.let {
                Text(
                    text = stringResource(id = R.string.related_recipes_label),
                    modifier = modifier.padding(vertical = 5.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    items(it) { similar ->
                        val isRecipeSaved = remember {
                            mutableStateOf(onRecipeSaved(similar?.id!!))
                        }
                        RecipeItem(
                            recipeResult = similar,
                            navController = navController,
                            isRecipeSaved = isRecipeSaved.value,
                            onSavedRecipeClick = {
                                isRecipeSaved.value = !isRecipeSaved.value
                                if (isRecipeSaved.value) {
                                    onSaveRecipe(similar!!)
                                } else {
                                    onRemoveRecipe(similar?.id!!)
                                }
                            }
                        )
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.preparation_label),
                modifier = modifier.padding(vertical = 5.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        items(recipe?.instructions ?: emptyList()) {
            InstructionListItem(instruction = it)
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
    val recipeResult = Recipe(
        name = "Homemade Chicken Shawarma With Ben Stiller And Ahmed Badr",
        description = "Shawarma is a Middle Eastern dish that’s become a " +
                "popular street food around the world, but it’s easy enough to " +
                "make in your own kitchen. This recipe calls for boneless skinless " +
                "chicken thighs that are marinated in a fragrant mixture of spices. " +
                "Bake the chicken in the oven, then toss it into pita bread with cucumber, " +
                "tomatoes, pickles, and a homemade white sauce made with Greek yogurt and " +
                "lemon juice.",
        num_servings = 4,
        nutrition = Nutrition(
            calories = 473,
            carbohydrates = 11,
            fat = 21,
            fiber = 1,
            protein = 60,
            sugar = 8
        ),
        thumbnail_url = "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/449dd02247f849a18f207082deff57c1/ShawarmaFBupload.jpg",
    )
    RecipeDetail(
        navController = navController,
        recipe = recipeResult,
        similarRecipe = RecipeList(results = listOf(recipeResult)),
        onRemoveRecipe = {},
        onSaveRecipe = {},
        onRecipeSaved = { false })
}
