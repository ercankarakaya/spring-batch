package com.ercan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jobs")
public class JobController {

    private Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;


    @GetMapping
    public BatchStatus loadCsvToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString("inputFile","/src/main/resources/persons.csv")
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job,jobParameters);

        LOGGER.info("JobExecution: "+jobExecution.getStatus());

        LOGGER.info("Batch is running...");
        while (jobExecution.isRunning()){
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }


}
