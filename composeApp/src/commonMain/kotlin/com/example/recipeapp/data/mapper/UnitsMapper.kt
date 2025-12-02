package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.UnitsDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Units

class UnitsMapper: BidirectionalMapper<Units?, UnitsDto?> {
    override fun toDomain(value: UnitsDto?): Units {
        return Units(
            abbreviation = value?.abbreviation,
            display_plural = value?.display_plural,
            display_singular = value?.display_singular,
            name = value?.name,
            system = value?.system,
        )
    }

    override fun fromDomain(value: Units?): UnitsDto {
        return UnitsDto(
            abbreviation = value?.abbreviation,
            display_plural = value?.display_plural,
            display_singular = value?.display_singular,
            name = value?.name,
            system = value?.system,
        )
    }
}
