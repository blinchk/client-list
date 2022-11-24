package ee.laus.clientlist.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private Long countryId;
}
