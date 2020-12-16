package com.kgc.kmall.bean;

import java.io.Serializable;

public class PmsBaseCatalog3 implements Serializable {
    private Long id;

    private String name;

    private Long catalog2Id;

    @Override
    public String toString() {
        return "PmsBaseCatalog3{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", catalog2Id=" + catalog2Id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(Long catalog2Id) {
        this.catalog2Id = catalog2Id;
    }
}