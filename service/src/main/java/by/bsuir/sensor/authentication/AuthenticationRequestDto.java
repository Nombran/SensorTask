package by.bsuir.sensor.authentication;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDto {
    @NonNull
    @NotBlank
    @Size(min = 5, max = 15)
    private String login;
    @NonNull
    @NotBlank
    @Size(min = 5, max = 15)
    private String password;
}
