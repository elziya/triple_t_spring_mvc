package ru.kpfu.itis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.utils.validation.SamePasswords;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SamePasswords(names = {"password", "passwordRepeat"})
public class SignUpForm {

    @Size(min = 2, max = 100, message = "Minimum length of first name: {min}, maximum: {max}")
    @NotBlank(message = "The first name can't be empty")
    private String firstName;

    @Size(min = 2, max = 100, message = "Minimum length of last name: {min}, maximum: {max}")
    @NotBlank(message = "The last name can't be empty")
    private String lastName;

    @Email
    @NotBlank(message = "The email can't be empty")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,50}$", message = "Your password is weak")
    @Size(min = 8, message = "Your password must contains at least {min} characters")
    @NotBlank(message = "The password can't be empty")
    private String password;

    @NotBlank(message = "The password can't be empty")
    private String passwordRepeat;
}
