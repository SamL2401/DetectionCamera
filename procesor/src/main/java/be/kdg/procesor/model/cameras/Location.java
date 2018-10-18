package be.kdg.procesor.model.cameras;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Location {
    @JsonAlias("long")
    private double longitude;
    @JsonAlias("lat")
    private double latitude;

}
