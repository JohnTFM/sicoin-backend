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
		lixeira.setLatitude(-16.606789D);
		lixeira.setLongitude(-49.24881D);
		lixeira.setPesoMaximo(1500D);
		lixeira.setVolumeMaximo(589D);
		lixeiraRepository.save(lixeira);

		em.detach(lixeira);

		lixeira.setId("L2");

		lixeiraRepository.save(lixeira);

	}


}
