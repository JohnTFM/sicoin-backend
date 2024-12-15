package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.model.evento.IniciarColetaEvent;
import br.ufg.sicoin.repository.CaminhaoRepository;
import br.ufg.sicoin.repository.IniciarColetaEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IniciarColetaListener {


    private final IniciarColetaEventRepository iniciarColetaEventRepository;

    @EventListener
    @Async
    public void handleIniciarColeta(IniciarColetaEvent iniciarColetaEvent) {

        iniciarColetaEventRepository.save(iniciarColetaEvent);

    }


}
