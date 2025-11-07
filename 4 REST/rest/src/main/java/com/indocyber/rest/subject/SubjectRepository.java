package com.indocyber.rest.subject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, String> {

    @Query("""
            SELECT s
            FROM Subject s
            WHERE (:name IS NULL OR s.name LIKE %:name%) AND
            (:active IS NULL OR s.active = :active)
            """)
    Page<Subject> findAll(String name, Boolean active, Pageable pageable);
}