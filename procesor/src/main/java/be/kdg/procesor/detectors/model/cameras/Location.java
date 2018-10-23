package be.kdg.procesor.detectors.model.cameras;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This class represents a Location
 * @author Sam Laureys
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    @JsonAlias({"long","longitude"})
    private double longitude;
    @JsonAlias({"lat","latitude"})
    private double latitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
