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

    @Column
    @NotNull
    @Length(min = 1, max = 255)
    private String status;

    public DataBaseInfoFile() {
    }

    public DataBaseInfoFile(@NotNull @Length(min = 3, max = 255) String path, @NotNull @Length(min = 1, max = 255) String name, @NotNull @Length(min = 1, max = 255) String status) {
        this.path = path;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
