package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.model.evento.FinalizarColetaEvent;
import br.ufg.sicoin.repository.FinalizarColetaEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinalizarColetaListener {

    private final FinalizarColetaEventRepository finalizarColetaEventRepository;

    @EventListener
    @Async
    public void handleColeta(FinalizarColetaEvent finalizarColetaEvent) {

        this.finalizarColetaEventRepository.save(finalizarColetaEvent);

    }

}
