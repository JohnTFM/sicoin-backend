package br.ufg.sicoin.event.listeners;

import br.ufg.sicoin.model.evento.LocalizacaoCaminhaoEvent;
import br.ufg.sicoin.repository.LocalizacaoCaminhaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizacaoCaminhaoListener {

    private final LocalizacaoCaminhaoRepository localizacaoCaminhaoRepository;

    @EventListener
    public void handle(LocalizacaoCaminhaoEvent event) {

        localizacaoCaminhaoRepository.save(event);

    }
}
