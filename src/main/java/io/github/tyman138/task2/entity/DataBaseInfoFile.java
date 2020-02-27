package io.github.tyman138.task2.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Files")
public class DataBaseInfoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull
    @Length(min = 3, max = 255)
    private String path;
    @Column
    @NotNull
    @Length(min = 1, max = 255)
    private String name;

    public DataBaseInfoFile() {
    }

    public DataBaseInfoFile(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
