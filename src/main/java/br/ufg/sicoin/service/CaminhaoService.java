package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.AtualizaEstadoCaminhao;
import br.ufg.sicoin.dto.RequisicaoDescobrirLixeirasDTO;
import br.ufg.sicoin.dto.RespostaDescobrirLixeiraDTO;
import br.ufg.sicoin.model.evento.EventoSicoin;
import br.ufg.sicoin.model.evento.FinalizarColetaEvent;
import br.ufg.sicoin.model.evento.IniciarColetaEvent;
import br.ufg.sicoin.exceptions.CaminhaoAusenteException;
import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.caminhao.EstadoCaminhao;
import br.ufg.sicoin.model.evento.LocalizacaoCaminhaoEvent;
import br.ufg.sicoin.repository.CaminhaoRepository;
import br.ufg.sicoin.repository.LocalizacaoCaminhaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CaminhaoService {

    private static final double RAIO_TERRA_KM = 6371.0;

    private final CaminhaoRepository caminhaoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final LixeiraService lixeiraService;

    private final LocalizacaoCaminhaoRepository localizacaoCaminhaoRepository;

    public Double calcularDistanciaPercorrida(LocalDate data, Long caminhaoId){

        // Converter LocalDate para Instant (fuso horário de Brasília)
        Instant inicioDoDia = data.atStartOfDay(ZoneId.of("America/Sao_Paulo")).toInstant();
        Instant fimDoDia = data.atTime(23, 59, 59).atZone(ZoneId.of("America/Sao_Paulo")).toInstant();

        // Buscar eventos no repositório pela data
        List<LocalizacaoCaminhaoEvent> eventosDoDia = localizacaoCaminhaoRepository
                .findByCriadoEmBetweenAndCaminhaoId(inicioDoDia, fimDoDia,caminhaoId);

        if (eventosDoDia.isEmpty()) {
            return 0.0; // Nenhuma distância percorrida
        }

        // Calcular a distância total usando a lógica existente
        return calcularDistanciaTotal(eventosDoDia);

    }

    public static double calcularDistanciaTotal(List<LocalizacaoCaminhaoEvent> eventos) {
        if (eventos == null || eventos.size() < 2) {
            return 0.0; // Sem distância para calcular
        }

        // Ordenar eventos pela data/hora (se necessário) (ASC)
        eventos.sort(Comparator.comparing(EventoSicoin::getCriadoEm));

        double distanciaTotal = 0.0;

        for (int i = 0; i < eventos.size() - 1; i++) {
            LocalizacaoCaminhaoEvent pontoAtual = eventos.get(i);
            LocalizacaoCaminhaoEvent proximoPonto = eventos.get(i + 1);

            double distancia = calcularDistanciaHaversine(
                    pontoAtual.getLatitude(),
                    pontoAtual.getLongitude(),
                    proximoPonto.getLatitude(),
                    proximoPonto.getLongitude()
            );

            distanciaTotal += distancia;
        }

        return distanciaTotal;
    }

    // Fórmula de Haversine
    private static double calcularDistanciaHaversine(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RAIO_TERRA_KM * c; // Distância em quilômetros
    }

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

       applicationEventPublisher.publishEvent(new LocalizacaoCaminhaoEvent(
               caminhaoAtualizar,
               informarCaminhaoDTO.getLatitude(),
               informarCaminhaoDTO.getLongitude()
       ));

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