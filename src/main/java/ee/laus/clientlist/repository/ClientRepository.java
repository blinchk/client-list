package ee.laus.clientlist.repository;

import ee.laus.clientlist.model.Client;
import ee.laus.clientlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByUser(User user);
}
