package com.ercan.batch;

import com.ercan.entity.Person;
import com.ercan.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

    private Logger LOGGER = LoggerFactory.getLogger(PersonDbWriter.class);


    @Override
    public Person process(Person person) throws Exception {
        String countryCode = person.getCountry();
        String country = Util.getCountryByCode(countryCode);
        person.setCountry(country);
        person.setModifyDate(new Date());
        LOGGER.info(String.format("Converted from [%s] to [%s] ",countryCode,country));
        return person;
    }

}
