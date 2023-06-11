package com.mitchelldederer.trackmateserver.jobs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface JobRepository extends CrudRepository<Job, Integer> {

    public Iterable<Job> findAllByJobStatus(JobStatus jobStatus);
}
