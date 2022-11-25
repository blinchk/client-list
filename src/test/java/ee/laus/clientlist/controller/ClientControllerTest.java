package ee.laus.clientlist.controller;

import ee.laus.clientlist.dto.ClientDto;
import ee.laus.clientlist.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientService clientService;

    @Test
    void findAll() {
        clientController.findAllByCurrentUser();
        verify(clientService).findAllByCurrentUser();
    }

    @Test
    void findById() {
        final Long id = 4L;
        clientController.findById(id);
        verify(clientService).findById(id);
    }

    @Test
    void create() {
        final ClientDto dto = new ClientDto();
        clientController.create(dto);
        verify(clientService).create(dto);
    }

    @Test
    void update() {
        final Long id = 4L;
        final ClientDto dto = new ClientDto();
        clientController.update(id, dto);
        verify(clientService).update(id, dto);
    }
}