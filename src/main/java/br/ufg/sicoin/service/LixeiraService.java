package br.ufg.sicoin.service;

import br.ufg.sicoin.dto.mqtt.LixeiraSinalDTO;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LixeiraService {

    private final LixeiraRepository lixeiraRepository;

    @Transactional
    public List<Lixeira> obterLixeirasProximas(Double lt, Double lg, Double distanciaKm, Double minimoVolume) {
        return lixeiraRepository.findNearbyLixeiras(lt, lg, distanciaKm, minimoVolume);
    }

    @Transactional
    public void atualizarLixeiraDoDTO(LixeiraSinalDTO dto){
        lixeiraRepository.atualizarLixeiraDoMqqtt(
                dto.getId(),
                dto.getPesoAtual(),
                dto.getVolumeAtual(),
                Instant.now()
        );
    }

    public List<Lixeira> obterTodasAsLixeiras()
    {
        return lixeiraRepository.findAllLixeiras();
    }
}