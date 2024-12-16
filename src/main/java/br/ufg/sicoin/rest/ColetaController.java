package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoInformarPossivelColetaDTO;
import br.ufg.sicoin.service.ColetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/coleta")
@AllArgsConstructor
public class ColetaController {

    private final ColetaService coletaService;

    @PostMapping("/informar")
    public ResponseEntity<Map<String,String>> informarPossivelColeta(@RequestBody RequisicaoInformarPossivelColetaDTO possivelColetaDTO){
        coletaService.emitirEventoColeta(possivelColetaDTO);
        return ResponseEntity.ok(Map.of("status","Coleta emitida com sucesso!"));
    }
}
