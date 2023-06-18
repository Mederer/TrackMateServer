package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.address.Address;
import com.mitchelldederer.trackmateserver.address.AddressRepository;
import com.mitchelldederer.trackmateserver.categories.Category;
import com.mitchelldederer.trackmateserver.categories.CategoryRepository;
import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;

    private final AddressRepository addressRepository;

    public JobService(JobRepository jobRepository, CategoryRepository categoryRepository, AddressRepository addressRepository) {
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
        this.addressRepository = addressRepository;
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
        Job job = jobRepository.findById(id).orElseThrow(AppEntityNotFoundException::new);
        return JobMapper.modelToDto(job);
    }

    public JobDTO createJob(CreateJobRequest jobRequest) {
        Job newJob = new Job();

        List<Category> categories = Arrays.stream(jobRequest.categoryIds()).mapToObj(category -> categoryRepository.findById(category).orElseThrow(AppEntityNotFoundException::new)).toList();
        Address address = addressRepository.findById(jobRequest.addressId()).orElseThrow(AppEntityNotFoundException::new);

        newJob.setJobName(jobRequest.jobName());
        newJob.setJobDescription(jobRequest.jobDescription());
        newJob.setJobStatus(JobStatus.WAITING);
        newJob.setCategories(categories);
        newJob.setAddress(address);

        jobRepository.save(newJob);

        return JobMapper.modelToDto(newJob);
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

    public JobDTO setJobAddress(int jobId, int addressId) {
        Job job = jobRepository.findById(jobId).orElseThrow(AppEntityNotFoundException::new);
        Address address = addressRepository.findById(addressId).orElseThrow(AppEntityNotFoundException::new);

        job.setAddress(address);

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
