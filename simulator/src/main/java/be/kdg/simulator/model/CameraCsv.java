package be.kdg.simulator.model;

import com.opencsv.bean.CsvBindByPosition;

public class CameraCsv {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String licencePlate;
    @CsvBindByPosition(position = 2)
    private long delay;
}
