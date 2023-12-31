package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.categories.CategoryDTO;

import java.util.List;

public record JobDTO(
        int jobId,
        String jobName,
        String jobDescription,
        JobStatus jobStatus,
        List<CategoryDTO> categories
) { }
