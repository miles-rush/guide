package com.miles.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class Voice {
    @Id
    @GeneratedValue
    private Integer id;

    private String filePath;

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},optional = false)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    public Voice() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
