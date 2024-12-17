package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.AtualizaEstadoCaminhao;
import br.ufg.sicoin.dto.RequisicaoDescobrirLixeirasDTO;
import br.ufg.sicoin.dto.RespostaDescobrirLixeiraDTO;
import br.ufg.sicoin.model.evento.FinalizarColetaEvent;
import br.ufg.sicoin.model.evento.IniciarColetaEvent;
import br.ufg.sicoin.exceptions.CaminhaoAusenteException;
import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import br.ufg.sicoin.repository.CaminhaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final LixeiraService lixeiraService;

    @Transactional
    public Caminhao atualizarDados(AtualizaEstadoCaminhao informarCaminhaoDTO) {
       Caminhao caminhaoAtualizar = caminhaoRepository.findById(informarCaminhaoDTO.getIdCaminhao()).orElseThrow(()->new CaminhaoAusenteException(informarCaminhaoDTO.getIdCaminhao()));

       boolean iniciouRota = !caminhaoAtualizar.getEstado().equals(EstadoCaminhao.EM_ROTA) && informarCaminhaoDTO.getEstadoCaminhao().equals(EstadoCaminhao.EM_ROTA);
       boolean finalizouRota = caminhaoAtualizar.getEstado().equals(EstadoCaminhao.EM_ROTA) && !informarCaminhaoDTO.getEstadoCaminhao().equals(EstadoCaminhao.EM_ROTA);

       if(iniciouRota){
            applicationEventPublisher.publishEvent(new IniciarColetaEvent(caminhaoAtualizar));
       }

       if(finalizouRota){
           applicationEventPublisher.publishEvent(new FinalizarColetaEvent(caminhaoAtualizar));
       }

       caminhaoAtualizar.setEstado(informarCaminhaoDTO.getEstadoCaminhao());
       caminhaoAtualizar.setLatitudeAtual(informarCaminhaoDTO.getLatitude());
       caminhaoAtualizar.setLongitudeAtual(informarCaminhaoDTO.getLongitude());

       return caminhaoAtualizar;
    }

    public RespostaDescobrirLixeiraDTO verificarLixeirasCheias(RequisicaoDescobrirLixeirasDTO informarCaminhaoDTO) {
        return new RespostaDescobrirLixeiraDTO(lixeiraService.obterLixeirasProximas(
                informarCaminhaoDTO.getLatitude(),
                informarCaminhaoDTO.getLongitude(),
                informarCaminhaoDTO.getDistanciaMaximaLixeira(),
                informarCaminhaoDTO.getVolumeMinimoLixeira()
        ));
    }

    public List<Caminhao> obterTodosOsCaminhoes()
    {
        return caminhaoRepository.findAllCaminhoes();
    }
}