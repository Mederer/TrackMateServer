package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.address.Address;
import com.mitchelldederer.trackmateserver.address.AddressDTO;
import com.mitchelldederer.trackmateserver.address.AddressMapper;
import com.mitchelldederer.trackmateserver.address.AddressRepository;
import com.mitchelldederer.trackmateserver.categories.Category;
import com.mitchelldederer.trackmateserver.categories.CategoryRepository;
import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public JobDTO createJob(CreateJobRequest createJobRequest) {
        Job job = new Job();

        job.setJobName(createJobRequest.jobName());
        job.setJobDescription(createJobRequest.jobDescription());
        job.setJobStatus(JobStatus.WAITING);

        if (createJobRequest.addressId().isPresent()) {
            Address address = addressRepository.findById(createJobRequest.addressId().get()).orElseThrow(AppEntityNotFoundException::new);
            job.setAddress(address);
        }

        if (createJobRequest.categories().isPresent()) {
            List<Integer> categoryIds = Arrays.asList(createJobRequest.categories().get());
            Iterable<Category> categoryList = categoryRepository.findAllById(categoryIds);
            job.setCategories((List<Category>)categoryList);
        }

        jobRepository.save(job);

        return JobMapper.modelToDto(job);
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

    public AddressDTO getJobAddress(int jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(AppEntityNotFoundException::new);
        Address address = job.getAddress();

        return AddressMapper.modelToDto(address);
    }

    public JobDTO updateJob(UpdateJobRequest updateJobRequest) {
        Job job = jobRepository.findById(updateJobRequest.jobId()).orElseThrow(AppEntityNotFoundException::new);

        job.setJobName(updateJobRequest.jobName());
        job.setJobDescription(updateJobRequest.jobDescription());
        job.setJobStatus(updateJobRequest.jobStatus());

        jobRepository.save(job);
        return JobMapper.modelToDto(job);
    }

    public void deleteJob(int jobId) {
        jobRepository.deleteById(jobId);
    }
}
