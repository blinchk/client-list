package ee.laus.clientlist.controller;

import ee.laus.clientlist.dto.ClientDto;
import ee.laus.clientlist.response.client.ClientListItemResponse;
import ee.laus.clientlist.response.client.ClientResponse;
import ee.laus.clientlist.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping
    public List<ClientListItemResponse> findAllByCurrentUser() {
        return clientService.findAllByCurrentUser();
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public ClientResponse create(@RequestBody @Valid ClientDto dto) {
        return clientService.create(dto);
    }

    @PostMapping("/{id}")
    public ClientResponse update(@PathVariable Long id, @RequestBody @Valid ClientDto dto) {
        return clientService.update(id, dto);
    }
}
