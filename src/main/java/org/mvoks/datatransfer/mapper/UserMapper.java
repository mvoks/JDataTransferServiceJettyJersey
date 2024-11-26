package org.mvoks.datatransfer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mvoks.datatransfer.dto.user.UserDto;
import org.mvoks.datatransfer.entity.user.User;

@Mapper(componentModel = "jakarta")
public interface UserMapper extends Mappable<User, UserDto> {

    @Mapping(target = "password", qualifiedByName = "obfuscation-password")
    @Override
    UserDto toDto(User user);

    @Named("obfuscation-password")
    default String obfuscationPassword(String password) {
        return "***";
    }
}