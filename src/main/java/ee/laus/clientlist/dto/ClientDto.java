package ee.laus.clientlist.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ClientDto {
    @NotBlank(message = "First name cannot be blank") private String firstName;
    @NotBlank(message = "Last name cannot be blank") private String lastName;
    @NotBlank(message = "Username cannot be blank") private String username;
    @NotBlank(message = "Email cannot be blank") private String email;
    private String address;
    @NotNull(message = "Country must be filled") private Long countryId;
}
