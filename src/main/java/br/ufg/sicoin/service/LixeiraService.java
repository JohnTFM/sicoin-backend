package br.ufg.sicoin.service;

import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LixeiraService {

    private final LixeiraRepository lixeiraRepository;

    @Transactional
    public List<Lixeira> obterLixeirasProximas(Double lt, Double lg,Double distanciaKm){
        return lixeiraRepository.findNearbyLixeiras(lt,lg,distanciaKm,0.01);
    }
    @Transactional
    public List<Lixeira> obterLixeirasProximas(Double lt, Double lg,Double distanciaKm, Double minimoVolume){
        return lixeiraRepository.findNearbyLixeiras(lt,lg,distanciaKm,minimoVolume);
    }


}
