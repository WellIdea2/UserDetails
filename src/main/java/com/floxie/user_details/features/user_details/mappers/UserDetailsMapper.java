package com.floxie.user_details.features.user_details.mappers;

import com.floxie.user_details.features.user_details.dto.UserDetailsCreateRequest;
import com.floxie.user_details.features.user_details.dto.UserDetailsEditRequest;
import com.floxie.user_details.features.user_details.entity.UserDetails;
import org.commons.feature.user_details.dto.UserDetailsView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserDetailsMapper {

  UserDetails toEntity(UserDetailsCreateRequest dto);

  UserDetailsView toView(UserDetails entity);

  void update(@MappingTarget UserDetails entity, UserDetailsEditRequest dto);
}
