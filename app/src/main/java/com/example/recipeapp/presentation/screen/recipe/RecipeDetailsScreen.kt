package com.example.recipeapp.presentation.screen.recipe

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.recipeapp.R
import com.example.recipeapp.domain.model.*
import com.example.recipeapp.domain.model.Unit
import com.example.recipeapp.presentation.component.*

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val recipeDetail = viewModel.uiState.collectAsState().value
    when {
        recipeDetail.isLoading -> {
            LoadingProgressBar()
        }
        recipeDetail.recipe != null -> {
            RecipeDetail(
                navController = navController,
                recipe = recipeDetail.recipe,
                similarRecipe = recipeDetail.similarRecipe
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
    Log.d("List Recipe Response", "Detail Screen Recipe = ${recipeDetail.recipe}")
    Log.d("List Recipe Response", "Detail Screen Similar = ${recipeDetail.similarRecipe}")
}

@Composable
fun RecipeDetail(
    modifier: Modifier = Modifier,
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
            items(similarRecipe?.results!!) { similar ->
                RecipeItem(
                    recipeResult = similar,
                    navController = navController,
                )
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
    val section = Section(
        name = "Marinade", components = listOf(
            Component(
                ingredient = Ingredient(name = "cumin"), measurements = listOf(
                    Measurement(quantity = "1", unit = Unit(name = "teaspoon"))
                )
            ),
            Component(
                ingredient = Ingredient(name = "ground cardamom"), measurements = listOf(
                    Measurement(quantity = "1", unit = Unit(name = "teaspoon")),
                )
            )
        )
    )
    val instruction = Instruction(
        display_text = "Combine all marinade ingredients in a large bowl and whisk together.",
        position = 1,
    )
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
    RecipeDetail(navController = navController, recipe = recipeResult)
}
