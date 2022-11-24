package ee.laus.clientlist.controller;

import ee.laus.clientlist.response.country.CountryResponse;
import ee.laus.clientlist.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryResponse> findAll() {
        return countryService.findAll();
    }
}
