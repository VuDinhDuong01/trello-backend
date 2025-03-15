package com.example.trello.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.trello.Dto.Request.UserRequest;
import com.example.trello.Entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // dùng để map firstName vào lastName
    // ignore tức là k mappering target
    @Mapping(source = "firstName", target = "lastName", ignore = true)
    UserEntity toUser(UserRequest.Register request);

    // map requet to userEntity , tự động mapper vào user luôn
    void updateUser(@MappingTarget UserEntity userEntity, UserRequest.Register request);
}
