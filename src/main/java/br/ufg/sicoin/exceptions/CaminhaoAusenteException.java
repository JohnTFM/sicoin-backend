package br.ufg.sicoin.exceptions;

public class CaminhaoAusenteException extends RuntimeException {
    public CaminhaoAusenteException(Long idCaminhao) {
        super("Caminhao de ID ' " + idCaminhao +" ' não existente. " );
    }
}
