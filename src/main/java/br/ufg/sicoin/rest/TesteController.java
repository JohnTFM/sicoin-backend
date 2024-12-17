package br.ufg.sicoin.rest;

import br.ufg.sicoin.event.events.ColetaCaminhaoEvent;
import br.ufg.sicoin.event.events.ColetaLixeiraEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class TesteController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/gerar-coleta-lixeira")
    public ResponseEntity<Void> gerarColetaLixeira(@RequestBody ColetaLixeiraEvent coletaLixeiraEvent) {
        applicationEventPublisher.publishEvent(coletaLixeiraEvent);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/gerar-coleta-caminhao")
    public ResponseEntity<Void> gerarColeta(@RequestBody ColetaCaminhaoEvent coletaCaminhaoEvent) {
        applicationEventPublisher.publishEvent(coletaCaminhaoEvent);
        return ResponseEntity.ok().build();
    }

}
