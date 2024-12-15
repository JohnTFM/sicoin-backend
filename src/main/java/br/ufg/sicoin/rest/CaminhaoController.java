package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoCaminhaoRotaDTO;
import br.ufg.sicoin.dto.RequisicaoInformarCaminhaoDTO;
import br.ufg.sicoin.dto.RespostaDescobrirLixeiraDTO;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.service.CaminhaoService;
import br.ufg.sicoin.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caminhao")
@RequiredArgsConstructor
public class CaminhaoController {

    private final GoogleMapsService googleMapsService;

    private final CaminhaoService caminhaoService;

    @PostMapping("/obter-rotas")
    public ResponseEntity<RespostaRotaDTO> obterRota(@RequestBody RequisicaoCaminhaoRotaDTO requisicaoCaminhaoRotaDTO){
        return ResponseEntity.ok(googleMapsService.criarRota(requisicaoCaminhaoRotaDTO));
    }

    @PostMapping("/informar-e-descobrir-lixeiras")
    public ResponseEntity<RespostaDescobrirLixeiraDTO> informar(@RequestBody RequisicaoInformarCaminhaoDTO informarCaminhaoDTO){

        caminhaoService.atualizarDados(informarCaminhaoDTO);

        return ResponseEntity.ok(caminhaoService.verificarLixeirasCheias(informarCaminhaoDTO));

    }



}
