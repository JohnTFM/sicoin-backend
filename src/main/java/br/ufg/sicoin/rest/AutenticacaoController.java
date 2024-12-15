package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoLoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/publico")
public class AutenticacaoController {

    @Value("${sicoin.keycloak.client-id}")
    public String clientId;

    @Value("${sicoin.keycloak.client-secret}")
    public String clientSecret;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    public String issuerUri;

    @Operation(summary = "Request Token", description = "Login ", security = @SecurityRequirement(name = "security_auth"))
    @PostMapping("/login")
    public ResponseEntity<String> token(@RequestBody RequisicaoLoginDTO user) {

        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", clientId);
        formData.add("username", user.login());
        formData.add("password", user.senha());
        formData.add("grant_type", "password");
        formData.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

        try {
            return rt.postForEntity(issuerUri + "/protocol/openid-connect/token",
                    entity,
                    String.class);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

}
