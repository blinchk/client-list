package ee.laus.clientlist.response.client;

import ee.laus.clientlist.response.country.CountryResponse;
import lombok.Data;

@Data
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private CountryResponse country;
}
