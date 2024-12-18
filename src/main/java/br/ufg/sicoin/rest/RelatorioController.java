package br.ufg.sicoin.rest;

import br.ufg.sicoin.model.caminhao.Caminhao;
import br.ufg.sicoin.model.evento.ColetaEvent;
import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.service.CaminhaoService;
import br.ufg.sicoin.service.ColetaService;
import br.ufg.sicoin.service.LixeiraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final LixeiraService lixeiraService;

    private final CaminhaoService caminhaoService;

    private final ColetaService coletaService;

    @GetMapping("/lixeiras")
    public List<Lixeira> obterTodasAsLixeirasParaRelatorio()
    {
        return lixeiraService.obterTodasAsLixeiras();
    }

    @GetMapping("/caminhoes")
    public List<Caminhao> obterTodosOsCaminhoesParaRelatorio()
    {
        return caminhaoService.obterTodosOsCaminhoes();
    }

    @GetMapping("/coleta-events")
    public List<ColetaEvent> obterTodosOsColetaEventsParaRelatorio()
    {
        return coletaService.obterTodosOsColetaEvents();
    }

    @GetMapping("/coleta-events-por-lixeira/{lixeiraId}")
    public List<ColetaEvent> obterColetaEventsPorLixeira(@PathVariable String lixeiraId){

        return coletaService.getColetaEventRepository().findAllByLixeiraId(lixeiraId);

    }

}