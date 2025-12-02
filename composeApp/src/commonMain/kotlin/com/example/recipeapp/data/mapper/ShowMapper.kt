package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.ShowDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Show

class ShowMapper: BidirectionalMapper<Show?, ShowDto?> {
    override fun toDomain(value: ShowDto?): Show {
        return Show(
            id = value?.id,
            name = value?.name
        )
    }

    override fun fromDomain(value: Show?): ShowDto {
        return ShowDto(
            id = value?.id,
            name = value?.name
        )
    }
}