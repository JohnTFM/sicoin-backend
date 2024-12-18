package br.ufg.sicoin.dto.googlemaps;

import lombok.Data;

import java.util.List;

@Data
public class DirectionsResponse {

    private List<GeocodedWaypoint> geocodedWaypoints;
    private List<Route> routes;
    private String status;

}

