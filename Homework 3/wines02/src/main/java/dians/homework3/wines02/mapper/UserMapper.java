package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.UserEntity;

public class UserMapper {
    public static UserEntity mapToUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .username(userDto.getUsername())
                .build();
    }

    public static UserDto mapToUserDto(UserEntity user) {
        return UserDto.builder()
                .username(user.getUsername())
                .build();
    }
}
