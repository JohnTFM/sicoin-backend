package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoCaminhaoRotaDTO;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caminhao/obter-rotas")
public class RotasController {

    @GetMapping
    public ResponseEntity<RespostaRotaDTO> obterRota(@RequestBody RequisicaoCaminhaoRotaDTO requisicaoCaminhaoRotaDTO){
        //obter a regiao a partir da ltitude e longitude
        //obter a lista de lixeiras topzera
        //pegar a rota utilizando o lixeiraService, e entregar os polylines

        return ResponseEntity.ok(null);
    }

}
