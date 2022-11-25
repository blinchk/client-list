package ee.laus.clientlist.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
    @NotBlank
    private String address;
    @ManyToOne
    private Country country;
    @ManyToOne
    private User user;

    public boolean isCreatedByUser(Long userId) {
        return userId.equals(user.getId());
    }

    public boolean isNotCreatedByUser(Long userId) {
        return !isCreatedByUser(userId);
    }
}
