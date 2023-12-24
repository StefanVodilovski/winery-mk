package dians.homework3.wines02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto implements Serializable {
    private Long Id;
    @NotEmpty(message = "Username can not be empty")
    private String username;
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
    @NotEmpty(message = "Address can not be empty")
    private String address;
    @NotEmpty(message = "Phone Number can not be empty")
    private String phoneNumber;
}