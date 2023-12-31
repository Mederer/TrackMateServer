package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.categories.CategoryMapper;

import java.util.stream.Collectors;

public class JobMapper {
    public static Job dtoToModel(JobDTO jobDto) {
        Job job = new Job();
        job.setJobId(jobDto.jobId());
        job.setJobName(jobDto.jobName());
        job.setJobDescription(jobDto.jobDescription());
        job.setJobStatus(jobDto.jobStatus());
        return job;
    }

    public static JobDTO modelToDto(Job job) {
        return new JobDTO(
                job.getJobId(),
                job.getJobName(),
                job.getJobDescription(),
                job.getJobStatus(),
                job.getCategories().stream().map(CategoryMapper::modelToDto).collect(Collectors.toList())
        );
    }
}
