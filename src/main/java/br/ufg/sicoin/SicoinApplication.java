package br.ufg.sicoin;

import br.ufg.sicoin.model.lixeira.Lixeira;
import br.ufg.sicoin.repository.LixeiraRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
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

		String[] latitudesLongitudes = {
				"-16.597528112043722, -49.2589120062313",
				"-16.59800411751784, -49.25646855683074",
				"-16.59532345430009, -49.25755193155494",
				"-16.595597586061402, -49.25799167545387",
				"-16.595867625921716, -49.2583503015462",
				"-16.596145848411368, -49.25874308250446",
				"-16.59641179600301, -49.258977897207764",
				"-16.596730932627338, -49.25876869865391",
				"-16.596460893979707, -49.258170988500034",
				"-16.596215403970856, -49.257876402781335",
				"-16.59592899856433, -49.25738115722526",
				"-16.59594127309051, -49.25681760193732",
				"-16.596428161996624, -49.257133534447235",
				"-16.596554998399967, -49.25735554107582",
				"-16.59686595180891, -49.25776539946705",
				"-16.597172813232515, -49.2581368336341",
				"-16.59737738725427, -49.258256375666015",
				"-16.597099166547014, -49.25706095535826",
				"-16.596681834731555, -49.25638639676634",
				"-16.597066434681484, -49.25604911747581"
		};

		for (int i = 0; i < latitudesLongitudes.length; i++) {
			Lixeira lixeira = getLixeira(latitudesLongitudes, i);
            log.info("Liexeira {} criada!", lixeira.getId());
			lixeiraRepository.save(lixeira);
		}
	}

	private static Lixeira getLixeira(String[] latitudesLongitudes, int i) {
		String[] coordinates = latitudesLongitudes[i].split(", ");
		Lixeira lixeira = new Lixeira();
		lixeira.setId("L" + (i + 1));
		lixeira.setLatitude(Double.parseDouble(coordinates[0]));
		lixeira.setLongitude(Double.parseDouble(coordinates[1]));
		lixeira.setPesoMaximo(200D); // Peso máximo fixo
		lixeira.setVolumeMaximo(200D); // Volume máximo fixo
		lixeira.setVolumeAtual(0D); // Volume atual fixo
		lixeira.setPesoAtual(0D); // Peso atual fixo
		lixeira.setDescricao("Lixeiras perto das ruas do supervi");
		return lixeira;
	}


}
