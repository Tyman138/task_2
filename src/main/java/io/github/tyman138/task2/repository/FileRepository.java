package io.github.tyman138.task2.repository;

import io.github.tyman138.task2.entity.DataBaseInfoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<DataBaseInfoFile, Long> {
}
