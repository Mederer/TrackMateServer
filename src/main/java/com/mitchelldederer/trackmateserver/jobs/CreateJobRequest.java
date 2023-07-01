package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.address.Address;

import java.util.Optional;

public record CreateJobRequest(
        String jobName,
        String jobDescription,

        Optional<Integer[]> categories,
        Optional<Integer> addressId
) {
}
