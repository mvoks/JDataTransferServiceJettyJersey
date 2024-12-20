package org.mvoks.datatransfer.mapper;

import org.mapstruct.Mapper;
import org.mvoks.datatransfer.dto.user.UserDto;
import org.mvoks.datatransfer.entity.user.User;

@Mapper(componentModel = "jakarta")
public interface UserMapper extends Mappable<User, UserDto> {
}