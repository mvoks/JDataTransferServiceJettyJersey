package org.mvoks.datatransfer.mapper;

interface Mappable<Entity, Dto> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);
}