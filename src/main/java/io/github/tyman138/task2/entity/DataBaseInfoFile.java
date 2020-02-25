package io.github.tyman138.task2.entity;

import javax.persistence.*;

@Entity
@Table(name = "Files")
public class DataBaseInfoFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String path;
    @Column
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
