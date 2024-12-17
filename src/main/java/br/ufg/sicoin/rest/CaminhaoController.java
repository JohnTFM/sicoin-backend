package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.*;
import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.service.CaminhaoService;
import br.ufg.sicoin.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caminhao")
@RequiredArgsConstructor
public class CaminhaoController {

    private final GoogleMapsService googleMapsService;

    private final CaminhaoService caminhaoService;

    @PostMapping("/obter-rotas")
    public ResponseEntity<RespostaRotaDTO> obterRota(@RequestBody RequisicaoRotaDTO requisicaoRotaDTO){
        return ResponseEntity.ok(googleMapsService.criarRota(requisicaoRotaDTO));
    }

    @PostMapping("/descobrir-lixeiras")
    public ResponseEntity<RespostaRotaDTO> descobrirLixeiras(@RequestBody RequisicaoDescobrirLixeirasDTO informarCaminhaoDTO){

        var lixeirasCheiasWrapper = caminhaoService.verificarLixeirasCheias(informarCaminhaoDTO);

        if(!lixeirasCheiasWrapper.getLixeirasProximasPreenchidas().isEmpty()){
            RequisicaoRotaDTO requisicaoRotaDTO = new RequisicaoRotaDTO();
            requisicaoRotaDTO.setKilometrosLimite(informarCaminhaoDTO.getDistanciaMaximaLixeira());
            requisicaoRotaDTO.setLatitude(informarCaminhaoDTO.getLatitude());
            requisicaoRotaDTO.setLongitude(informarCaminhaoDTO.getLongitude());
            return ResponseEntity.ok(googleMapsService.criarRota(requisicaoRotaDTO,informarCaminhaoDTO.getVolumeMinimoLixeira()));
        }

        RespostaRotaDTO response = new RespostaRotaDTO();

        response.setBackendStatus("SEM_LIXEIRAS");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/registrar-estado-caminhao")
    public ResponseEntity<CaminhaoDTO> alterarEstadoCaminhao(@RequestBody RequisicaoIniciarRotaDTO requisicaoIniciarRotaDTO){

        return ResponseEntity.ok(new CaminhaoDTO(caminhaoService.atualizarDados(requisicaoIniciarRotaDTO)));
    }


}
