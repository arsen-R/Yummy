package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.CreditDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Credit

class CreditMapper: BidirectionalMapper<Credit?, CreditDto?> {
    override fun toDomain(value: CreditDto?): Credit {
        return Credit(
            name = value?.name,
            type = value?.type,
        )
    }

    override fun fromDomain(value: Credit?): CreditDto {
        return CreditDto(
            name = value?.name,
            type = value?.type,
        )
    }
}