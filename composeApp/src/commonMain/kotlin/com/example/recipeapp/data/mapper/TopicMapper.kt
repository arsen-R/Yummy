package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.network.dto.TopicDto
import com.example.recipeapp.domain.mapper.BidirectionalMapper
import com.example.recipeapp.domain.model.Topic

class TopicMapper: BidirectionalMapper<Topic?, TopicDto?> {
    override fun toDomain(value: TopicDto?): Topic {
        return Topic(
            name = value?.name,
            slug = value?.slug
        )
    }

    override fun fromDomain(value: Topic?): TopicDto {
        return TopicDto(
            name = value?.name,
            slug = value?.slug
        )
    }

}
