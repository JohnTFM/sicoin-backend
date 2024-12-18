package br.ufg.sicoin.model.evento;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IniciarColetaEvent.class, name = "IniciarColetaEvent"),
        @JsonSubTypes.Type(value = FinalizarColetaEvent.class, name = "FinalizarColetaEvent"),
        @JsonSubTypes.Type(value = LocalizacaoCaminhaoEvent.class, name = "LocalizacaoCaminhaoEvent")
})
public abstract class EventoSicoin {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @CreationTimestamp
    @Column
    Instant criadoEm;

    public abstract String getTipo();

}
