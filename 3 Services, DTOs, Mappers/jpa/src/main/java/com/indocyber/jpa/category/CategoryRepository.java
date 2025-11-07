package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.PricePerCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findByNameLike(Pageable pageable, String name);

//    @Query(value = "EXEC PricePerCategories", nativeQuery = true)
    @Procedure(procedureName = "pricePerCategories")
    List<Object[]> findAllPricePerCategories();
}
