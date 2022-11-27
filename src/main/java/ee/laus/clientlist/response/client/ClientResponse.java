package ee.laus.clientlist.response.client;

import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String username;
    private Long countryId;
}
