package be.kdg.procesor.detectors.model.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a LicensePlate
 *
 * @author Sam Laureys
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicensePlate {
    private String plateId;
    private String nationalNumber;
    private int euroNumber;

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public int getEuroNumber() {
        return euroNumber;
    }

    public void setEuroNumber(int euroNumber) {
        this.euroNumber = euroNumber;
    }

    @Override
    public String toString() {
        return "LicensePlate{" +
                "plateId='" + plateId + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                ", euroNumber=" + euroNumber +
                '}';
    }
}
