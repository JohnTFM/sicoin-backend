package br.ufg.sicoin.dto.googlemaps;

import lombok.Data;

import java.util.List;

@Data
public  class Route {
    private Bounds bounds;
    private String copyrights;
    private List<Leg> legs;
    private OverviewPolyline overviewPolyline;

}
