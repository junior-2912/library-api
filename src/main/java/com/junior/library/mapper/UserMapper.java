package com.junior.library.mapper;

import com.junior.library.dto.UserRequestDTO;
import com.junior.library.dto.UserResponseDTO;
import com.junior.library.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activeLoansQuantity", ignore = true)
    User requestToUserEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO entityToUserResponse(User user);

    List<UserResponseDTO> entityListToUserResponseList(List<User> user);
}
