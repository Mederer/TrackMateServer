package com.mitchelldederer.trackmateserver.jobs;

import java.util.Optional;

public record CreateJobRequest (
        String jobName,
        String jobDescription,
        int[] categoryIds,
        int addressId
) {
}
