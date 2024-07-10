package org.example.module10homework2.mappers;

import org.example.module10homework2.dto.PostDTO;
import org.example.module10homework2.entity.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    Post toEntity(PostDTO postDTO);

    PostDTO toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDTO postDTO, @MappingTarget Post post);
}