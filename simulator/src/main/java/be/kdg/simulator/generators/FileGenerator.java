package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private InputStream inputStream;
    private BufferedReader br;

    public FileGenerator() {
    }

    @PostConstruct
    private void Initialize() {
        inputStream = getClass().getResourceAsStream("/csvFiles/example.csv");
        br = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public Optional<CameraMessage> generate() {
        CameraMessage cameraMessage;
        Optional<CameraMessage> optionalCameraMessage = Optional.empty();
        try {
            LOGGER.info("Next line is being parsed");
            String line = br.readLine();
            if (line != null) {
                String[] split = line.split(String.valueOf(','));
                if (split.length != 3) {
                    throw new ParseException("MessageFileReaderImpl: Error whilst parsing the CSV file", split.length);
                }

                Thread.sleep(Integer.parseInt(split[2]));
                cameraMessage = new CameraMessage(Integer.parseInt(split[0]), split[1], LocalDateTime.now());
                optionalCameraMessage = Optional.of(cameraMessage);
            } else {
                inputStream.close();
            }

        } catch (IOException e) {
            LOGGER.error("MessageFileReaderImpl: Error whilst reading the CSV file");
        } catch (ParseException e) {
            LOGGER.warn(e.getLocalizedMessage());
        } catch (InterruptedException e) {
            LOGGER.warn(e.getLocalizedMessage());
        }
        return optionalCameraMessage;
    }
}
