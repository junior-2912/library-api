package com.junior.library.mapper;

import com.junior.library.dto.UserRequestDTO;
import com.junior.library.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    User requestToUserEntity(UserRequestDTO userRequestDTO);
}
