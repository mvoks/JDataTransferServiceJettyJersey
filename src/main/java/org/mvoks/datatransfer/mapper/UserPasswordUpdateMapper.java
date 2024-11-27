package org.mvoks.datatransfer.mapper;

import org.mapstruct.Mapper;
import org.mvoks.datatransfer.dto.user.UserPasswordUpdateDto;
import org.mvoks.datatransfer.entity.user.User;

@Mapper(componentModel = "jakarta")
public interface UserPasswordUpdateMapper extends Mappable<User, UserPasswordUpdateDto> {
}