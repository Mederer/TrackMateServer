package com.mitchelldederer.trackmateserver;

import com.mitchelldederer.trackmateserver.address.Address;
import com.mitchelldederer.trackmateserver.address.AddressRepository;
import com.mitchelldederer.trackmateserver.address.State;
import com.mitchelldederer.trackmateserver.categories.Category;
import com.mitchelldederer.trackmateserver.categories.CategoryRepository;
import com.mitchelldederer.trackmateserver.jobs.Job;
import com.mitchelldederer.trackmateserver.jobs.JobRepository;
import com.mitchelldederer.trackmateserver.jobs.JobStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TrackMateServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackMateServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner addTestData(JobRepository jobRepository,
                                         AddressRepository addressRepository,
                                         CategoryRepository categoryRepository) {
        return runner -> {
            List<Category> categoryList = new ArrayList<>();
            categoryList.add(new Category(0, "Electrical", "Electrical work", null));

            List<Address> addressList = new ArrayList<>();
            addressList.add(new Address(0, "104", "River Street", "West Kempsey", State.NSW, "2440"));

            List<Job> jobList = new ArrayList<>();
            jobList.add(new Job(0, "House rewire", "House needs a rewire", JobStatus.WAITING, null, null));

            jobList.get(0).setAddress(addressList.get(0));
            jobList.get(0).setCategories(categoryList);

            categoryRepository.saveAll(categoryList);
            addressRepository.saveAll(addressList);
            jobRepository.saveAll(jobList);
        };
    }

}
