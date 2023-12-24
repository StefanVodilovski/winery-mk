package dians.homework3.wines02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long Id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
//    private byte[] photo;
    private String token;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
