package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.categories.CategoryDTO;

import java.util.Set;

public record JobDTO(
        int jobId,
        String jobName,
        String jobDescription,
        JobStatus jobStatus,
        Set<CategoryDTO> categories
) { }
