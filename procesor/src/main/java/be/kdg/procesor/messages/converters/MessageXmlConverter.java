package be.kdg.procesor.messages.converters;

import be.kdg.procesor.messages.model.messages.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for converting a xml to a CameraMessage object or inverse
 *
 * @author Sam Laureys
 * @version 1.01
 */
@Component
public class MessageXmlConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageXmlConverter.class);
    private final XmlMapper xmlMapper;
    private final JavaTimeModule javaTimeModule;

    public MessageXmlConverter(XmlMapper xmlMapper, JavaTimeModule javaTimeModule) {
        this.xmlMapper = xmlMapper;
        this.javaTimeModule = javaTimeModule;
    }

    @PostConstruct
    private void Initialize() {
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        xmlMapper.registerModule(javaTimeModule);
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public CameraMessage toMessage(String xml) throws IOException {
        LOGGER.info("Create Message from Xml");
        return xmlMapper.readValue(xml, CameraMessage.class);
    }

    public String toXml(CameraMessage cameraMessage) throws JsonProcessingException {
        LOGGER.info("Create Xml string from Message");
        return xmlMapper.writeValueAsString(cameraMessage);
    }

}
