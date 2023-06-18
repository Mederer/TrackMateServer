package com.mitchelldederer.trackmateserver.jobs;

public record CreateJobRequest (
        String jobName,
        String jobDescription,
        int[] categoryIds,
        int addressId
) {
}
