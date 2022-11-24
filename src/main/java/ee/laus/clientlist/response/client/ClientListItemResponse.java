package ee.laus.clientlist.response.client;

import lombok.Data;

@Data
public class ClientListItemResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
}
