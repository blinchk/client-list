package ee.laus.clientlist.service;

import ee.laus.clientlist.repository.CountryRepository;
import ee.laus.clientlist.response.country.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository repository;
    private final ModelMapper mapper;

    public List<CountryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(country -> mapper.map(country, CountryResponse.class))
                .toList();
    }
}
