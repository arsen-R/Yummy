package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.TotalTimeTierDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.TotalTimeTier

class TotalTimeTierMapper: BidirectionalMapper<TotalTimeTier?, TotalTimeTierDto?> {
    override fun toDomain(value: TotalTimeTierDto?): TotalTimeTier {
        return TotalTimeTier(
            display_tier = value?.display_tier,
            tier = value?.tier
        )
    }

    override fun fromDomain(value: TotalTimeTier?): TotalTimeTierDto {
        return TotalTimeTierDto(
            display_tier = value?.display_tier,
            tier = value?.tier
        )
    }
}
