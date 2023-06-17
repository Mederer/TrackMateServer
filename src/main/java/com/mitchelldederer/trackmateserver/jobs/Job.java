package com.mitchelldederer.trackmateserver.jobs;

import com.mitchelldederer.trackmateserver.address.Address;
import com.mitchelldederer.trackmateserver.categories.Category;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity(name = "jobs")
public class Job {

    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "job_categories",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;


    public Job() {
    }

    public Job(int jobId, String jobName, String jobDescription, JobStatus jobStatus, Address address, List<Category> categories) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.jobStatus = jobStatus;
        this.address = address;
        this.categories = categories;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobStatus=" + jobStatus +
                ", categories=" + categories +
                '}';
    }
}
