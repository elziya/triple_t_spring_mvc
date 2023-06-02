package ru.kpfu.itis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.Account;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String email;


    public static ProfileDto from(Account account) {
        return ProfileDto.builder()
                .email(account.getEmail())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }
}
