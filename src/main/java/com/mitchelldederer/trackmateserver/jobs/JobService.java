package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.categories.Category;
import com.mitchelldederer.trackmateserver.categories.CategoryRepository;
import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;

    public JobService(JobRepository jobRepository, CategoryRepository categoryRepository) {
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<JobDTO> getJobs(Optional<JobStatus> status) {
        Iterable<Job> jobs;

        if (status.isPresent()) {
            jobs = jobRepository.findAllByJobStatus(status.get());
        } else {
            jobs = jobRepository.findAll();
        }

        List<JobDTO> jobDTOList = new ArrayList<>();

        jobs.forEach((job -> jobDTOList.add(JobMapper.modelToDto(job))));
        return jobDTOList;
    }

    public JobDTO getJob(int id) {
        Job job =  jobRepository.findById(id).orElseThrow(AppEntityNotFoundException::new);
        return JobMapper.modelToDto(job);
    }

    public void createJob(JobDTO job) {
        Job newJob = JobMapper.dtoToModel(job);
        newJob.setJobStatus(JobStatus.WAITING);
        System.out.println("Saving Job: " + newJob);
        jobRepository.save(newJob);
    }

    public JobDTO addCategoryToJob(int jobId, int categoryId) {
        Job job = jobRepository.findById(jobId).orElseThrow(AppEntityNotFoundException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(AppEntityNotFoundException::new);

        job.getCategories().add(category);
        jobRepository.save(job);
        return JobMapper.modelToDto(job);
    }

    public JobDTO removeCategoryFromJob(int jobId, int categoryId) {
        Job job = jobRepository.findById(jobId).orElseThrow(AppEntityNotFoundException::new);
        Category category = categoryRepository.findById(categoryId).orElseThrow(AppEntityNotFoundException::new);

        job.getCategories().remove(category);
        jobRepository.save(job);

        return JobMapper.modelToDto(job);
    }

    public JobDTO updateJob(JobDTO jobDTO) {
        Job job = jobRepository.findById(jobDTO.jobId()).orElseThrow(AppEntityNotFoundException::new);

        job.setJobStatus(jobDTO.jobStatus());
        job.setJobName(jobDTO.jobName());
        job.setJobDescription(jobDTO.jobDescription());

        jobRepository.save(job);
        return JobMapper.modelToDto(job);
    }

    public void deleteJob(int jobId) {
        jobRepository.deleteById(jobId);
    }
}
