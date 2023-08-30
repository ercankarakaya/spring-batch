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

import java.util.HashMap;
import java.util.Map;

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
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job,parameters);

        LOGGER.info("JobExecution: "+jobExecution.getStatus());

        LOGGER.info("Batch is running...");
        while (jobExecution.isRunning()){
            System.out.println("...");
        }

        return jobExecution.getStatus();
    }


}
