package br.ufg.sicoin;

import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SicoinApplication {

	@Autowired
	LixeiraRepository lixeiraRepository;

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(SicoinApplication.class, args);
	}

	@Value("${sicoin.iniciar-dados}")
	Boolean iniciarDados;

	@PostConstruct
	@Transactional
	public void init() {

		if (!Boolean.TRUE.equals(iniciarDados)) {
			return;
		}
		// Lixeira 1 - Condomínio Boreal
		Lixeira lixeira1 = new Lixeira();
		lixeira1.setId("L1");
		lixeira1.setLatitude(-16.595453197788743);
		lixeira1.setLongitude(-49.2624442018294);
		lixeira1.setPesoMaximo(150D);
		lixeira1.setVolumeMaximo(58D);
		lixeira1.setVolumeAtual(5D);
		lixeira1.setDescricao("Lixeira do meu condomínio (Condomínio Boreal)");
		lixeiraRepository.save(lixeira1);

		// Lixeira 2 - Próximo à academia perto da UFG
		Lixeira lixeira2 = new Lixeira();
		lixeira2.setId("L3");
		lixeira2.setLatitude(-16.59878392965019);
		lixeira2.setLongitude(-49.259560087449245);
		lixeira2.setPesoMaximo(150D);
		lixeira2.setVolumeMaximo(60D);
		lixeira2.setVolumeAtual(12D);
		lixeira2.setDescricao("Lixeira em frente à academia perto da UFG");
		lixeiraRepository.save(lixeira2);

		// Lixeira 3 - Praça do Setor Itatiaia
		Lixeira lixeira3 = new Lixeira();
		lixeira3.setId("L2");
		lixeira3.setLatitude(-16.5997296069534);
		lixeira3.setLongitude(-49.25819863242834);
		lixeira3.setPesoMaximo(180D);
		lixeira3.setVolumeMaximo(70D);
		lixeira3.setVolumeAtual(20D);
		lixeira3.setDescricao("Lixeira localizada na Praça do Setor Itatiaia");
		lixeiraRepository.save(lixeira3);

		// Lixeira 4 - Área residencial próxima à UFG ,
		Lixeira lixeira4 = new Lixeira();
		lixeira4.setId("L4");
		lixeira4.setLatitude(-16.608543059444784);
		lixeira4.setLongitude(-49.252117657351604);
		lixeira4.setPesoMaximo(160D);
		lixeira4.setVolumeMaximo(65D);
		lixeira4.setVolumeAtual(15D);
		lixeira4.setDescricao("Lixeira em área residencial próxima à UFG");
		lixeiraRepository.save(lixeira4);
	}



}
