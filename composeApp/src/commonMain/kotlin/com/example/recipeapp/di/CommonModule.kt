package com.example.recipeapp.di

import com.example.recipeapp.MainActivityViewModel
import com.example.recipeapp.data.database.RecipeDatabase
import com.example.recipeapp.data.database.dao.RecipeDao
import com.example.recipeapp.data.mapper.BrandMapper
import com.example.recipeapp.data.mapper.CompilationMapper
import com.example.recipeapp.data.mapper.ComponentMapper
import com.example.recipeapp.data.mapper.CreditMapper
import com.example.recipeapp.data.mapper.IngredientMapper
import com.example.recipeapp.data.mapper.InstructionMapper
import com.example.recipeapp.data.mapper.LinkerdRecipeMapper
import com.example.recipeapp.data.mapper.MeasurementMapper
import com.example.recipeapp.data.mapper.NutritionMapper
import com.example.recipeapp.data.mapper.PriceMapper
import com.example.recipeapp.data.mapper.RecipeEntityMapper
import com.example.recipeapp.data.mapper.RecipeMapper
import com.example.recipeapp.data.mapper.RecipeResultMapper
import com.example.recipeapp.data.mapper.RenditionMapper
import com.example.recipeapp.data.mapper.SectionMapper
import com.example.recipeapp.data.mapper.ShowMapper
import com.example.recipeapp.data.mapper.TagMapper
import com.example.recipeapp.data.mapper.TagsListMapper
import com.example.recipeapp.data.mapper.TopicMapper
import com.example.recipeapp.data.mapper.TotalTimeTierMapper
import com.example.recipeapp.data.mapper.UnitsMapper
import com.example.recipeapp.data.mapper.UserRatingsMapper
import com.example.recipeapp.data.network.RecipeDataSource
import com.example.recipeapp.data.repository.AuthRepositoryImpl
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.data.repository.SettingsRepositoryImpl
import com.example.recipeapp.domain.repository.AuthRepository
import com.example.recipeapp.domain.repository.RecipeRepository
import com.example.recipeapp.domain.repository.SettingsRepository
import com.example.recipeapp.presentation.screen.account_management.AccountManagementViewModel
import com.example.recipeapp.presentation.screen.favorite.FavoriteViewModel
import com.example.recipeapp.presentation.screen.home.HomeViewModel
import com.example.recipeapp.presentation.screen.profile.SettingsViewModel
import com.example.recipeapp.presentation.screen.recipe_detail.RecipeDetailViewModel
import com.example.recipeapp.presentation.screen.search.SearchViewModel
import com.example.recipeapp.presentation.screen.signin.SignInViewModel
import com.example.recipeapp.presentation.screen.signup.SignUpViewModel
import com.example.recipeapp.presentation.screen.start.AuthViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "https://tasty.p.rapidapi.com"
                    headers.append(
                        "X-RapidAPI-Key",
                        "856bebe788mshff1e35c3cc55068p104c2djsn64f4262291f5"
                    )
                    headers.append("X-RapidAPI-Host", "tasty.p.rapidapi.com")
                }
            }
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "Http Client", message = message)
                    }
                }
            }.also {
                Napier.base(DebugAntilog())
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }
        }
    }
    single { RecipeDataSource(client = get()) }
}

val repositoryModule = module {
    factory<RecipeRepository> { RecipeRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
    factory<SettingsRepository> { SettingsRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { RecipeDetailViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    //viewModel { MainViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { AccountManagementViewModel(get()) }
}
val mapperModule = module {
    single {
        RecipeMapper(
            brandMapper = get(),
            compilationMapper = get(),
            creditMapper = get(),
            instructionMapper = get(),
            nutritionMapper = get(),
            priceMapper = get(),
            renditionMapper = get(),
            sectionMapper = get(),
            showMapper = get(),
            tagMapper = get(),
            topicMapper = get(),
            userRatingsMapper = get(),
            totalTimeTierMapper = get()
        )
    }
    single { RecipeResultMapper(recipeMapper = get()) }
    single { BrandMapper() }
    single { CompilationMapper(showMapper = get()) }
    single { ShowMapper() }
    single { CreditMapper() }
    single { InstructionMapper() }
    single { NutritionMapper() }
    single { PriceMapper() }
    single { RenditionMapper() }
    single { SectionMapper(componentMapper = get()) }
    single {
        ComponentMapper(
            ingredientMapper = get(),
            linkerdRecipeMapper = get(),
            measurementMapper = get()
        )
    }
    single { IngredientMapper() }
    single { LinkerdRecipeMapper() }
    single { MeasurementMapper(unitsMapper = get()) }
    single { UnitsMapper() }
    single { TagsListMapper(tagMapper = get()) }
    single { TagMapper() }
    single { UserRatingsMapper() }
    single { TotalTimeTierMapper() }
    single { TopicMapper() }
    single { RecipeEntityMapper() }
}

val databaseModule = module {
    single<RecipeDao> { get<RecipeDatabase>().recipeDao() }
}

expect fun platformModule(): Module