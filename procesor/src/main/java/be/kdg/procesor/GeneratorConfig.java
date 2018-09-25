package be.kdg.procesor;

import be.kdg.procesor.generators.FileGenerator;
import be.kdg.procesor.generators.MessageGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    // als je de source cod niet hebt
    //de Naam van deze clase is default de naam van deze methode
    //kan aangepast worden tussen de haakjes
    //Eerste mogelijkheid gebreik @qualifier
    //tweede boven commandline Messaenger extra @ConditionalProperty
    /*@Bean("fileGenerator")
    public MessageGenerator fileGenerator() {
        return new FileGenerator();
    }*/
}
