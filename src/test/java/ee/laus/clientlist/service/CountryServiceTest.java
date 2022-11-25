package ee.laus.clientlist.service;

import ee.laus.clientlist.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    @InjectMocks
    CountryService countryService;
    @Mock
    CountryRepository countryRepository;

    @Test
    void findAll() {
        countryService.findAll();
        verify(countryRepository).findAll();
    }
}