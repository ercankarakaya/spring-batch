package com.ercan.batch;

import com.ercan.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Locale;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

    private Logger LOGGER = LoggerFactory.getLogger(PersonDbWriter.class);


    @Override
    public Person process(Person person) throws Exception {
        String countryCode = person.getCountry();
        String country = getCountryByCode(countryCode);
        person.setCountry(country);
        person.setModifyDate(new Date());
        LOGGER.info(String.format("Converted from [%s] to [%s] ",countryCode,country));
        return person;
    }

    private String getCountryByCode(String code){
        String[] countries = Locale.getISOCountries();
        for (String country:countries){
            Locale locale = new Locale("en",country);
            if (locale.getCountry().equals(code)){
                return locale.getDisplayCountry();
            }
        }
        return "";
    }
}
