package com.test.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class which is used to
 *
 * @author Tarun Rohila
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Product() {}

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method to get the value of id
     *
     * @return id - id
     */
    public long getId() {
        return id;
    }

    /**
     * Method to set the value of id
     *
     * @param id - id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method to get the value of name
     *
     * @return name - name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the value of name
     *
     * @param name - name
     */
    public void setName(String name) {
        this.name = name;
    }
}
