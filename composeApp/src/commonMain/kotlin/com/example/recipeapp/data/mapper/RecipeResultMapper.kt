package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.RecipeResultDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.RecipeResult

class RecipeResultMapper(
    private val recipeMapper: RecipeMapper
) : BidirectionalMapper<RecipeResult?, RecipeResultDto?> {
    override fun toDomain(value: RecipeResultDto?): RecipeResult {
        return RecipeResult(
            count = value?.count,
            results = value?.results?.map { recipeMapper.toDomain(it) }
        )
    }

    override fun fromDomain(value: RecipeResult?): RecipeResultDto {
        return RecipeResultDto(
            count = value?.count,
            results = value?.results?.map { recipeMapper.fromDomain(it) }
        )
    }
}