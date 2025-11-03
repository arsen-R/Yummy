package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.database.entity.RecipeEntity
import com.example.recipeapp.data.network.dto.BrandDto
import com.example.recipeapp.data.network.dto.CompilationDto
import com.example.recipeapp.data.network.dto.ComponentDto
import com.example.recipeapp.data.network.dto.CreditDto
import com.example.recipeapp.data.network.dto.IngredientDto
import com.example.recipeapp.data.network.dto.InstructionDto
import com.example.recipeapp.data.network.dto.LinkedRecipeDto
import com.example.recipeapp.data.network.dto.MeasurementDto
import com.example.recipeapp.data.network.dto.NutritionDto
import com.example.recipeapp.data.network.dto.PriceDto
import com.example.recipeapp.data.network.dto.RecipeDto
import com.example.recipeapp.data.network.dto.RecipeResultDto
import com.example.recipeapp.data.network.dto.RenditionDto
import com.example.recipeapp.data.network.dto.SectionDto
import com.example.recipeapp.data.network.dto.ShowDto
import com.example.recipeapp.data.network.dto.TagDto
import com.example.recipeapp.data.network.dto.TagsListDto
import com.example.recipeapp.data.network.dto.TopicDto
import com.example.recipeapp.data.network.dto.TotalTimeTierDto
import com.example.recipeapp.data.network.dto.UnitDto
import com.example.recipeapp.data.network.dto.UserRatingsDto
import com.example.recipeapp.domain.model.Brand
import com.example.recipeapp.domain.model.Compilation
import com.example.recipeapp.domain.model.Component
import com.example.recipeapp.domain.model.Credit
import com.example.recipeapp.domain.model.Ingredient
import com.example.recipeapp.domain.model.Instruction
import com.example.recipeapp.domain.model.LinkedRecipe
import com.example.recipeapp.domain.model.Measurement
import com.example.recipeapp.domain.model.Nutrition
import com.example.recipeapp.domain.model.Price
import com.example.recipeapp.domain.model.Recipe
import com.example.recipeapp.domain.model.RecipeResult
import com.example.recipeapp.domain.model.Rendition
import com.example.recipeapp.domain.model.Section
import com.example.recipeapp.domain.model.Show
import com.example.recipeapp.domain.model.Tag
import com.example.recipeapp.domain.model.TagsList
import com.example.recipeapp.domain.model.Topic
import com.example.recipeapp.domain.model.TotalTimeTier
import com.example.recipeapp.domain.model.Units
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.domain.model.UserRatings
import com.google.firebase.auth.FirebaseUser


fun CompilationDto.toCompilation(): Compilation {
    return Compilation(
        approved_at = this.approved_at,
        aspect_ratio = this.aspect_ratio,
        beauty_url = this.beauty_url,
        buzz_id = this.buzz_id,
        canonical_id = this.canonical_id,
        country = this.country,
        created_at = this.created_at,
        description = this.description,
        draft_status = this.draft_status,
        id = this.id,
        is_shoppable = this.is_shoppable,
        keywords = this.keywords,
        language = this.language,
        name = this.name,
        promotion = this.promotion,
        show = this.show?.map { it.toShow() },
        slug = this.slug,
        thumbnail_alt_text = this.thumbnail_alt_text,
        thumbnail_url = this.thumbnail_url,
        video_id = this.video_id,
        video_url = this.video_url,
    )
}

fun ShowDto.toShow(): Show {
    return Show(
        id = this.id,
        name = this.name
    )
}

fun ComponentDto.toComponent(): Component {
    return Component(
        extra_comment = this.extra_comment,
        id = this.id,
        ingredient = this.ingredient?.toIngredient(),
        linked_recipe = this.linked_recipe?.toLinkedRecipe(),
        measurements = this.measurements?.map { it.toMeasurement() },
        position = this.position,
        raw_text = this.raw_text,
    )
}

fun LinkedRecipeDto.toLinkedRecipe(): LinkedRecipe {
    return LinkedRecipe(
        approved_at = this.approved_at,
        id = this.id,
        name = this.name,
        slug = this.slug,
        thumbnail_url = this.thumbnail_url
    )
}

fun MeasurementDto.toMeasurement(): Measurement {
    return Measurement(
        id = this.id,
        quantity = this.quantity,
        unit = this.unit?.toUnit()
    )
}

fun UnitDto.toUnit(): Units {
    return Units(
        abbreviation = this.abbreviation,
        display_plural = this.display_plural,
        display_singular = this.display_singular,
        name = this.name,
        system = this.system,
    )
}

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        created_at = this.created_at,
        display_plural = this.display_plural,
        display_singular = this.display_singular,
        id = this.id,
        name = this.name,
        updated_at = this.updated_at,
    )
}

fun CreditDto.toCredit(): Credit {
    return Credit(
        name = this.name,
        type = this.type,
    )
}

fun InstructionDto.toInstruction(): Instruction {
    return Instruction(
        appliance = this.appliance,
        display_text = this.display_text,
        end_time = this.end_time,
        id = this.id,
        position = this.position,
        start_time = this.start_time,
        temperature = this.temperature,
    )
}

fun NutritionDto.toNutrition(): Nutrition {
    return Nutrition(
        calories = this.calories,
        carbohydrates = this.carbohydrates,
        fat = this.fat,
        fiber = this.fiber,
        protein = this.protein,
        sugar = this.sugar,
        updated_at = this.updated_at,
    )
}

fun PriceDto.toPrice(): Price {
    return Price(
        consumption_portion = this.consumption_portion,
        consumption_total = this.consumption_total,
        portion = this.portion,
        total = this.total,
        updated_at = this.updated_at,
    )
}

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        approved_at = this.approved_at,
        aspect_ratio = this.aspect_ratio,
        beauty_url = this.beauty_url,
        buzz_id = this.buzz_id,
        brand = this.brand?.toBrand(),
        canonical_id = this.canonical_id,
        compilations = this.compilations?.map { it.toCompilation() },
        cook_time_minutes = this.cook_time_minutes,
        country = this.country,
        created_at = this.created_at,
        credits = this.credits?.map { it.toCredit() },
        description = this.description,
        draft_status = this.draft_status,
        id = this.id,
        inspired_by_url = this.inspired_by_url,
        instructions = this.instructions?.map { it.toInstruction() },
        is_one_top = this.is_one_top,
        is_shoppable = this.is_shoppable,
        keywords = this.keywords,
        language = this.language,
        name = this.name,
        num_servings = this.num_servings,
        nutrition = this.nutrition?.toNutrition(),
        nutrition_visibility = this.nutrition_visibility,
        original_video_url = this.original_video_url,
        prep_time_minutes = this.prep_time_minutes,
        price = this.price?.toPrice(),
        promotion = this.promotion,
        renditions = this.renditions?.map { it.toRendition() },
        sections = this.sections?.map { it.toSection() },
        seo_path = this.seo_path,
        seo_title = this.seo_title,
        servings_noun_plural = this.servings_noun_plural,
        servings_noun_singular = this.servings_noun_singular,
        show = this.show?.toShow(),
        show_id = this.show_id,
        slug = this.slug,
        tags = this.tags?.map { it.toTag() },
        thumbnail_alt_text = this.thumbnail_alt_text,
        thumbnail_url = this.thumbnail_url,
        tips_and_ratings_enabled = this.tips_and_ratings_enabled,
        topics = this.topics?.map { it.toTopic() },
        total_time_minutes = this.total_time_minutes,
        total_time_tier = this.total_time_tier?.toTotalTimeTier(),
        updated_at = this.updated_at,
        user_ratings = this.user_ratings?.toUserRatings(),
        video_ad_content = this.video_ad_content,
        video_id = this.video_id,
        video_url = this.video_url,
        yields = this.yields,
    )
}

fun RecipeResultDto.toRecipeResult(): RecipeResult {
    return RecipeResult(
        count = this.count,
        results = this.results?.map { it?.toRecipe() }
    )
}

fun UserRatingsDto.toUserRatings(): UserRatings {
    return UserRatings(
        count_negative = this.count_negative,
        count_positive = this.count_positive,
        score = this.score
    )
}

fun TotalTimeTierDto.toTotalTimeTier(): TotalTimeTier {
    return TotalTimeTier(
        display_tier = this.display_tier,
        tier = this.tier
    )
}

fun TopicDto.toTopic(): Topic {
    return Topic(
        name = this.name,
        slug = this.slug
    )
}

fun TagDto.toTag(): Tag {
    return Tag(
        display_name = this.display_name,
        id = this.id,
        name = this.name,
        type = this.type
    )
}


fun SectionDto.toSection(): Section {
    return Section(
        components = this.components?.map { it?.toComponent()!! },
        name = this.name,
        position = this.position,
    )
}

fun RenditionDto.toRendition(): Rendition {
    return Rendition(
        aspect = this.aspect,
        bit_rate = this.bit_rate,
        container = this.container,
        content_type = this.content_type,
        duration = this.duration,
        file_size = this.file_size,
        height = this.height,
        maximum_bit_rate = this.maximum_bit_rate,
        minimum_bit_rate = this.minimum_bit_rate,
        name = this.name,
        poster_url = this.poster_url,
        url = this.url,
        width = this.width,
    )
}

fun BrandDto.toBrand(): Brand {
    return Brand(
        id = this.id,
        image_url = this.image_url,
        name = this.name,
        slug = this.slug
    )
}


fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        approved_at = this.approved_at,
        aspect_ratio = this.aspect_ratio,
        beauty_url = this.beauty_url,
        buzz_id = this.buzz_id,
        //brand = this.brand?.toBrand(),
        canonical_id = this.canonical_id,
        //compilations = this.compilations?.map { it.toCompilation() },
        cook_time_minutes = this.cook_time_minutes,
        country = this.country,
        created_at = this.created_at,
        //credits = this.credits?.map { it.toCredit() },
        description = this.description,
        draft_status = this.draft_status,
        id = this.id,
        inspired_by_url = this.inspired_by_url,
        //instructions = this.instructions?.map { it.toInstruction() },
        is_one_top = this.is_one_top,
        is_shoppable = this.is_shoppable,
        keywords = this.keywords,
        language = this.language,
        name = this.name,
        num_servings = this.num_servings,
        //nutrition = this.nutrition?.toNutrition(),
        nutrition_visibility = this.nutrition_visibility,
        original_video_url = this.original_video_url,
        prep_time_minutes = this.prep_time_minutes,
        //price = this.price?.toPrice(),
        promotion = this.promotion,
        //recipes = this.recipes?.map { it.toRecipe() },
        //renditions = this.renditions?.map { it.toRendition() },
        //sections = this.sections?.map { it.toSection() },
        seo_path = this.seo_path,
        seo_title = this.seo_title,
        servings_noun_plural = this.servings_noun_plural,
        servings_noun_singular = this.servings_noun_singular,
        //show = this.show?.toShow(),
        show_id = this.show_id,
        slug = this.slug,
        //tags = this.tags?.map { it.toTag() },
        thumbnail_alt_text = this.thumbnail_alt_text,
        thumbnail_url = this.thumbnail_url,
        tips_and_ratings_enabled = this.tips_and_ratings_enabled,
        //topics = this.topics?.map { it.toTopic() },
        total_time_minutes = this.total_time_minutes,
        //total_time_tier = this.total_time_tier?.toTotalTimeTier(),
        updated_at = this.updated_at,
        //user_ratings = this.user_ratings?.toUserRatings(),
        video_ad_content = this.video_ad_content,
        video_id = this.video_id,
        video_url = this.video_url,
        yields = this.yields,
    )
}

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        approved_at = this.approved_at,
        aspect_ratio = this.aspect_ratio,
        beauty_url = this.beauty_url,
        buzz_id = this.buzz_id,
        //brand = this.brand?.toBrand(),
        canonical_id = this.canonical_id,
        //compilations = this.compilations?.map { it.toCompilation() },
        cook_time_minutes = this.cook_time_minutes,
        country = this.country,
        created_at = this.created_at,
        // credits = this.credits?.map { it.toCredit() },
        description = this.description,
        draft_status = this.draft_status,
        id = this.id,
        inspired_by_url = this.inspired_by_url,
        //instructions = this.instructions?.map { it.toInstruction() },
        is_one_top = this.is_one_top,
        is_shoppable = this.is_shoppable,
        keywords = this.keywords,
        language = this.language,
        name = this.name,
        num_servings = this.num_servings,
        //nutrition = this.nutrition?.toNutrition(),
        nutrition_visibility = this.nutrition_visibility,
        original_video_url = this.original_video_url,
        prep_time_minutes = this.prep_time_minutes,
        //price = this.price?.toPrice(),
        promotion = this.promotion,
        //recipes = this.recipes?.map { it.toRecipe() },
        //renditions = this.renditions?.map { it.toRendition() },
        //sections = this.sections?.map { it.toSection() },
        seo_path = this.seo_path,
        seo_title = this.seo_title,
        servings_noun_plural = this.servings_noun_plural,
        servings_noun_singular = this.servings_noun_singular,
        //show = this.show?.toShow(),
        show_id = this.show_id,
        slug = this.slug,
        //tags = this.tags?.map { it.toTag() },
        thumbnail_alt_text = this.thumbnail_alt_text,
        thumbnail_url = this.thumbnail_url,
        tips_and_ratings_enabled = this.tips_and_ratings_enabled,
        //topics = this.topics?.map { it.toTopic() },
        total_time_minutes = this.total_time_minutes,
        //total_time_tier = this.total_time_tier?.toTotalTimeTier(),
        updated_at = this.updated_at,
        //user_ratings = this.user_ratings?.toUserRatings(),
        video_ad_content = this.video_ad_content,
        video_id = this.video_id,
        video_url = this.video_url,
        yields = this.yields,
    )
}

fun TagsListDto.toTagsList(): TagsList {
    return TagsList(
        count = this.count,
        results = this.results
    )
}

fun FirebaseUser.toUser(): User {
    return User(
        uid = this.uid,
        email = this.email,
        provider = this.providerData[1].providerId
    )
}