package io.github.tyman138.task2.entity;

import javax.persistence.*;

@Entity
@Table(name ="Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String mark;
    @Column
    private int year;
    @Column
    private String color;
    @Column
    private String countryFactory;

    public Car() {
    }

    public Car(String mark, int year, String color, String countryFactory) {
        this.mark = mark;
        this.year = year;
        this.color = color;
        this.countryFactory = countryFactory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountryFactory() {
        return countryFactory;
    }

    public void setCountryFactory(String countryFactory) {
        this.countryFactory = countryFactory;
    }
}
