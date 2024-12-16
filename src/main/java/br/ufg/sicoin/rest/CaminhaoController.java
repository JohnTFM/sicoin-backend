package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.RequisicaoRotaDTO;
import br.ufg.sicoin.dto.RequisicaoDescobrirLixeirasDTO;
import br.ufg.sicoin.dto.RespostaDescobrirLixeiraDTO;
import br.ufg.sicoin.dto.RespostaRotaDTO;
import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
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
    public ResponseEntity<RespostaRotaDTO> informar(@RequestBody RequisicaoDescobrirLixeirasDTO informarCaminhaoDTO){

        caminhaoService.atualizarDados(informarCaminhaoDTO);

        if(!informarCaminhaoDTO.getEstadoCaminhao().equals(EstadoCaminhao.EM_ROTA)){
            return ResponseEntity.ok(null);
        }

        var lixeirasCheiasWrapper = caminhaoService.verificarLixeirasCheias(informarCaminhaoDTO);

        if(!lixeirasCheiasWrapper.getLixeirasProximasPreenchidas().isEmpty()){
            RequisicaoRotaDTO requisicaoRotaDTO = new RequisicaoRotaDTO();
            requisicaoRotaDTO.setKilometrosLimite(informarCaminhaoDTO.getDistanciaMaximaLixeira());
            requisicaoRotaDTO.setLatitude(informarCaminhaoDTO.getLatitude());
            requisicaoRotaDTO.setLongitude(informarCaminhaoDTO.getLongitude());
            return ResponseEntity.ok(googleMapsService.criarRota(requisicaoRotaDTO));
        }

        RespostaRotaDTO response = new RespostaRotaDTO();

        response.setBackendStatus("SEM_LIXEIRAS");

        return ResponseEntity.ok(response);

    }


}
