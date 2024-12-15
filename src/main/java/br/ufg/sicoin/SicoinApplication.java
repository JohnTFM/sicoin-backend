package br.ufg.sicoin;

import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

	@PostConstruct
	@Transactional
	public void init() {
		Lixeira lixeira = new Lixeira();
		lixeira.setId("L1");
		//Lixeira do meu condomínio (Condomínio Boreal)
		lixeira.setLatitude(-16.595453197788743D);
		lixeira.setLongitude(-49.2624442018294D);
		lixeira.setPesoMaximo(1500D);
		lixeira.setVolumeMaximo(589D);
		lixeiraRepository.save(lixeira);

		em.detach(lixeira);
		//Lixeira em frente à academia perto da UFG
		lixeira.setLatitude(-16.59878392965019);
		lixeira.setLongitude(-49.259560087449245);
		lixeira.setId("L3");

		lixeiraRepository.save(lixeira);

		em.detach(lixeira);

		lixeira.setId("L2");
		lixeira.setLatitude(-16.606142443742005);
		lixeira.setLongitude(-49.255259793705314);

		lixeiraRepository.save(lixeira);

	}


}
