package test.siret.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import test.siret.service.SiretService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/company")
public class SiretController {

    final static String URL_SIRET = "https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SiretService siretService;

    @GetMapping("/{siret}")
    public ResponseEntity<String> getCompanyBySiret(@PathVariable Long siret) throws IOException {
        ResponseEntity<String> data = restTemplate.getForEntity(URL_SIRET + siret, String.class);
        siretService.transferData(data.getBody());
        return data;
    }
}
