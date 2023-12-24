package dians.homework3.wines02.mapper;

import dians.homework3.wines02.dto.RegistrationDto;
import dians.homework3.wines02.dto.UserDto;
import dians.homework3.wines02.model.UserEntity;

public class UserMapper {
    public static UserEntity mapToUserEntity(UserDto userDto) {
        return UserEntity.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .Id(userDto.getId())
                .address(userDto.getAddress())
                .createdOn(userDto.getCreatedOn())
                .updatedOn(userDto.getUpdatedOn())
                .phoneNumber(userDto.getPhoneNumber())
                .photoUrl(userDto.getPhotoUrl())
                .build();
    }

    public static UserDto mapToUserDto(UserEntity user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .Id(user.getId())
                .address(user.getAddress())
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .phoneNumber(user.getPhoneNumber())
                .photoUrl(user.getPhotoUrl())
                .build();
    }
}
