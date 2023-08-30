package com.ercan.batch;

import com.ercan.entity.Person;
import com.ercan.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDbWriter implements ItemWriter<Person> {

    private Logger LOGGER = LoggerFactory.getLogger(PersonDbWriter.class);

    @Autowired
    private PersonRepository personRepository;


    @Override
    public void write(List<? extends Person> persons) throws Exception {
        LOGGER.info("Person Data Saved: "+persons);
        personRepository.saveAll(persons);
    }
}
