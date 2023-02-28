package com.example.recipeapp.presentation.screen.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.recipeapp.domain.model.RecipeList
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.presentation.component.*

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val recipeDetail = viewModel.uiState.collectAsStateWithLifecycle().value

    Scaffold(topBar = {
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
                        modifier = modifier.padding(it)
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
        })
}

@Composable
fun RecipeDetail(
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    navController: NavController,
    recipe: RecipeResult? = null,
    similarRecipe: RecipeList? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            text = recipe?.name!!,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            fontFamily = FontFamily.SansSerif,
        )
        recipe.description?.let {
            Text(
                text = it,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
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
                .padding(vertical = 10.dp, horizontal = 25.dp),
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
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
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            recipe.sections?.forEach {
                IngredientItemListHeader(ingredient = it)
            }
        }
        similarRecipe?.results?.let {
            Text(
                text = stringResource(id = R.string.related_recipes_label),
                modifier = modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 5.dp)
            ) {
                items(it) { similar ->
                    val isRecipeSaved = remember {
                        mutableStateOf(viewModel.isRecipeAdded(similar?.id!!))
                    }
                    RecipeItem(
                        recipeResult = similar,
                        navController = navController,
                        isRecipeSaved = isRecipeSaved.value,
                        onSavedRecipeClick = {
                            isRecipeSaved.value = !isRecipeSaved.value
                            if (isRecipeSaved.value) {
                                viewModel.insertRecipe(similar!!)
                            } else {
                                viewModel.removeRecipe(similar?.id!!)
                            }
                        }
                    )
                }
            }
        }

        Text(
            text = stringResource(id = R.string.preparation_label),
            modifier = modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            recipe.instructions?.forEach {
                InstructionListItem(instruction = it)
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
    val recipeResult = RecipeResult(
        name = "Homemade Chicken Shawarma With Ben Stiller And Ahmed Badr",
        description = "Shawarma is a Middle Eastern dish that’s become a " +
                "popular street food around the world, but it’s easy enough to " +
                "make in your own kitchen. This recipe calls for boneless skinless " +
                "chicken thighs that are marinated in a fragrant mixture of spices. " +
                "Bake the chicken in the oven, then toss it into pita bread with cucumber, " +
                "tomatoes, pickles, and a homemade white sauce made with Greek yogurt and " +
                "lemon juice.",
        num_servings = 4,

        )
    RecipeDetail(navController = navController, recipe = recipeResult, viewModel = hiltViewModel())
}
