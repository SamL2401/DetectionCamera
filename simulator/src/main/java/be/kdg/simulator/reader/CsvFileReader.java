package be.kdg.simulator.reader;

import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileReader implements MessageFileReader {

    private final Logger LOGGER = LoggerFactory.getLogger(CsvFileReader.class);
    private final char DEFAULT_SEPARATOR = ',';

    //@Value("${csvfile.resources.location}")
    private String pathToResourceFile = "/csvFiles/example.csv";

    @Override
    public List<CameraMessage> parseCameraMessageList() {
        return parseCameraMessageList(DEFAULT_SEPARATOR);
    }

    @Override
    public List<CameraMessage> parseCameraMessageList(char separator) {
        List<CameraMessage> cameraMessages = new ArrayList<>();

        InputStream inputStream = getClass().getResourceAsStream(pathToResourceFile);
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
}

