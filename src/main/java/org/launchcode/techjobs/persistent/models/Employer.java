package org.launchcode.techjobs.persistent.models;

import com.google.protobuf.Internal;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank(message = "Location is required")
    @Size(min=3,max = 100, message = "Location must be between 3 and 100 Characters")
    public String location;

    @OneToMany
    @JoinColumn(name = "employer_id")
    private  List<Job> jobs = new ArrayList<>();

    public Employer() {
    }

    // Initialize the id and value fields.
    public Employer(String location) {
        super();
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location= location;
    }
    public List<Job> getJobs() {
        return jobs;
    }
    public void addJob(Job jobs)
    {
        this.jobs.add(jobs);
    }

}
