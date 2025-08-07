package com.dtalk.Dtalks_UserMock.mapper;

import com.dtalk.Dtalks_UserMock.dto.UserResponse;
import com.dtalk.Dtalks_UserMock.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", expression = "java(user.getStatus().name())")
    UserResponse toResponse(User user);
}
