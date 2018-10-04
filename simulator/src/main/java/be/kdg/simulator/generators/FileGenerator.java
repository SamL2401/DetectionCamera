package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import javafx.scene.Camera;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
@ConditionalOnProperty(name="generator.type",havingValue = "file")
public class FileGenerator implements MessageGenerator  {

    private CameraMessage makeCameraMessageFromCsv()  {
        CameraMessage cameraMessage = new CameraMessage();
        /*try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Sam\\Documents\\school\\Kdg 3\\DetectionCamera\\simulator\\src\\main\\java\\be\\kdg\\simulator\\csvFiles\\example.csv"));
            scanner.useDelimiter(",");
            int index = 0;
            while (scanner.hasNext()) {
                if (index%3 == 0){
                    cameraMessage.setId(Integer.parseInt(scanner.next()));
                } else if (index%3 == 1){
                    cameraMessage.setLicensePlate(scanner.next());
                } else if (index%3 == 2){
                    wait(Long.parseLong(scanner.next()));
                    cameraMessage.setTimestamp(LocalDateTime.now());
                }
                index++;
            }
            scanner.close();
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }*/

        String csvFile = "C:\\Users\\Sam\\Documents\\school\\Kdg 3\\DetectionCamera\\simulator\\src\\main\\java\\be\\kdg\\simulator\\csvFiles\\example.csv";
        String line ;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] list = line.split(cvsSplitBy);

                cameraMessage.setId(Integer.parseInt(list[0]));
                cameraMessage.setLicensePlate(list[1]);
                wait(Long.parseLong(list[2]));
                cameraMessage.setTimestamp(LocalDateTime.now());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return cameraMessage;
    }

    @Override
    public CameraMessage generate() {
            return makeCameraMessageFromCsv();
    }
}
