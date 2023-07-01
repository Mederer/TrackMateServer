package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.address.AddressMapper;
import com.mitchelldederer.trackmateserver.categories.CategoryMapper;

import java.util.stream.Collectors;

public class JobMapper {
    public static JobDTO modelToDto(Job job) {
        return new JobDTO(
                job.getJobId(), job.getJobName(), job.getJobDescription(), job.getJobStatus()
        );
    }
}
