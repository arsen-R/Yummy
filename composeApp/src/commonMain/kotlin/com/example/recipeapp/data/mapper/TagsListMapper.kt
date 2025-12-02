package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.TagsListDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.TagsList

class TagsListMapper(
    private val tagMapper: TagMapper
): BidirectionalMapper<TagsList?, TagsListDto?> {
    override fun toDomain(value: TagsListDto?): TagsList {
        return TagsList(
            count = value?.count,
            results = value?.results?.map { tagMapper.toDomain(it) }
        )
    }

    override fun fromDomain(value: TagsList?): TagsListDto {
        return TagsListDto(
            count = value?.count,
            results = value?.results?.map { tagMapper.fromDomain(it) }
        )
    }

}
