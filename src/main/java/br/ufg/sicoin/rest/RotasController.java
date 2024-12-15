package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoCaminhaoRotaDTO;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import br.ufg.sicoin.service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caminhao/obter-rotas")
public class RotasController {

    @Autowired
    GoogleMapsService googleMapsService;

    @PostMapping
    public ResponseEntity<RespostaRotaDTO> obterRota(@RequestBody RequisicaoCaminhaoRotaDTO requisicaoCaminhaoRotaDTO){
        return ResponseEntity.ok(googleMapsService.criarRota(requisicaoCaminhaoRotaDTO));
    }

}
