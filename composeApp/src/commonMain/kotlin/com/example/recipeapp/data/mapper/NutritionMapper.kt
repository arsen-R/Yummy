package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.NutritionDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Nutrition

class NutritionMapper: BidirectionalMapper<Nutrition?, NutritionDto?> {
    override fun toDomain(value: NutritionDto?): Nutrition {
        return Nutrition(
            calories = value?.calories,
            carbohydrates = value?.carbohydrates,
            fat = value?.fat,
            fiber = value?.fiber,
            protein = value?.protein,
            sugar = value?.sugar,
            updated_at = value?.updated_at,
        )
    }

    override fun fromDomain(value: Nutrition?): NutritionDto {
        return NutritionDto(
            calories = value?.calories,
            carbohydrates = value?.carbohydrates,
            fat = value?.fat,
            fiber = value?.fiber,
            protein = value?.protein,
            sugar = value?.sugar,
            updated_at = value?.updated_at,
        )
    }
}