package br.ufg.sicoin.dto.googlemaps;

import lombok.Data;

import java.util.List;

@Data
public  class GeocodedWaypoint {
    private String geocoderStatus;
    private String placeId;
    private List<String> types;

}
