package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.categories.Category;
import com.mitchelldederer.trackmateserver.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobService {
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;

    public JobService(JobRepository jobRepository, CategoryRepository categoryRepository) {
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<JobDTO> getJobs(JobStatus status) {
        Iterable<Job> jobs;

        if (status != null) {
            jobs = jobRepository.findAllByJobStatus(status);
        } else {
            jobs = jobRepository.findAll();
        }

        List<JobDTO> jobDTOList = new ArrayList<>();

        jobs.forEach((job -> jobDTOList.add(JobMapper.modelToDto(job))));
        return jobDTOList;
    }

    public Optional<JobDTO> getJob(int id) {
        return jobRepository.findById(id).map(JobMapper::modelToDto);
    }

    public void createJob(JobDTO job) {
        Job newJob = JobMapper.dtoToModel(job);
        newJob.setJobStatus(JobStatus.WAITING);
        System.out.println("Saving Job: " + newJob);
        jobRepository.save(newJob);
    }

    public Optional<JobDTO> addCategoryToJob(int jobId, int categoryId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalJob.isEmpty() || optionalCategory.isEmpty()) {
            return Optional.empty();
        } else {
            Job job = optionalJob.get();
            Category category = optionalCategory.get();
            job.getCategories().add(category);
            jobRepository.save(job);

            return Optional.of(JobMapper.modelToDto(job));
        }
    }

    public Optional<JobDTO> removeCategoryFromJob(int jobId, int categoryId) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalJob.isEmpty() || optionalCategory.isEmpty()) {
            return Optional.empty();
        } else {
            Job job = optionalJob.get();
            Category category = optionalCategory.get();
            job.getCategories().remove(category);
            jobRepository.save(job);

            return Optional.of(JobMapper.modelToDto(job));
        }
    }

    public Optional<JobDTO> updateStatus(int jobId, JobStatus status) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);

        if (optionalJob.isEmpty()) {
            return Optional.empty();
        } else {
            Job job = optionalJob.get();
            job.setJobStatus(status);
            jobRepository.save(job);
            return Optional.of(JobMapper.modelToDto(job));
        }
    }
}
