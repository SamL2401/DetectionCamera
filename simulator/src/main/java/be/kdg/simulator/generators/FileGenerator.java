package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import javafx.scene.Camera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@ConditionalOnProperty(name="generator.type",havingValue = "file")
public class FileGenerator implements MessageGenerator  {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

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

    public List<CameraMessage> parseCameraMessageList(char separator) {
        List<CameraMessage> cameraMessages = new ArrayList<>();

        InputStream inputStream = getClass().getResourceAsStream("/csvFiles/example.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            LocalDateTime currentTime = LocalDateTime.now();

            LOGGER.info("MessageFileReaderImpl: CSV file is being read and parsed");

            while ((line = br.readLine()) != null) {
                String[] split = line.split(String.valueOf(separator));

                if (split.length != 3) {
                    throw new ParseException("MessageFileReaderImpl: Error whilst parsing the CSV file", split.length);
                }
                //TODO: moet ik hier al nagaan of de nummerplaat geldig is? (of gebeurt dit in de processor)
                currentTime = currentTime.plus(Integer.parseInt(split[2]), ChronoField.MILLI_OF_DAY.getBaseUnit());
                cameraMessages.add(new CameraMessage(Integer.parseInt(split[0]), split[1], currentTime));
            }
        } catch (IOException e) {
            LOGGER.error("MessageFileReaderImpl: Error whilst reading the CSV file");
        } catch (ParseException e) {
            LOGGER.warn(e.getLocalizedMessage());
        } finally {
            {
                //always close the inputstream after usage !!
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.warn("MessageFileReaderImpl: Error whilst closing the the input stream of the CSV file");
                }
            }
        }

        return cameraMessages;
    }

    @Override
    public CameraMessage generate() {
            return makeCameraMessageFromCsv();
    }
}
