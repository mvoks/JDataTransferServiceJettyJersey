package org.mvoks.datatransfer.mapper;

import org.mapstruct.Mapper;
import org.mvoks.datatransfer.dto.user.UserRegistrationDto;
import org.mvoks.datatransfer.entity.user.User;

@Mapper(componentModel = "jakarta")
public interface UserRegistrationMapper extends Mappable<User, UserRegistrationDto> {
}