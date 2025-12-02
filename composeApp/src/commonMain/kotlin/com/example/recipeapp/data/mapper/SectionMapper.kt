package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.SectionDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Section

class SectionMapper(
    private val componentMapper: ComponentMapper,
): BidirectionalMapper<Section?, SectionDto?> {
    override fun toDomain(value: SectionDto?): Section {
        return Section(
            components = value?.components?.map { componentMapper.toDomain(it) },
            name = value?.name,
            position = value?.position,
        )
    }

    override fun fromDomain(value: Section?): SectionDto {
        return SectionDto(
            components = value?.components?.map { componentMapper.fromDomain(it) },
            name = value?.name,
            position = value?.position,
        )
    }

}
