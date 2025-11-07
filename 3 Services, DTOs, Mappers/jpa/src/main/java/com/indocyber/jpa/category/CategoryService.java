package com.indocyber.jpa.category;

import com.indocyber.jpa.category.dto.CategoryListResponse;
import com.indocyber.jpa.category.dto.CategoryResponse;
import com.indocyber.jpa.category.dto.PricePerCategoryResponse;
import com.indocyber.jpa.category.dto.UpsertCategoryRequest;
import com.indocyber.jpa.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    //    private final CategoryMapper categoryMapper;
    private final CategoryStructMapper categoryMapper;

    public Page<CategoryListResponse> findAllCategories(Integer pageNumber, Integer size, String name) {
        Pageable pageable = PageRequest.of((pageNumber - 1), size);
        return categoryRepository.findByNameLike(pageable, name).map(categoryMapper::toListDto);
    }

    public CategoryResponse findCategoryById(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> getResourceNotFoundException(id));
        return categoryMapper.toDto(category);
    }

    public CategoryResponse insertCategory(UpsertCategoryRequest dto) {
        var category = categoryMapper.toEntity(dto);
        var saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }

    public CategoryResponse updateCategory(Integer id, UpsertCategoryRequest dto) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> getResourceNotFoundException(id));

        categoryMapper.updateEntityFromDto(category, dto);
        var saved = categoryRepository.save(category);

        return categoryMapper.toDto(saved);
    }

    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw getResourceNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PricePerCategoryResponse> pricePerCategories() {
        List<Object[]> results = categoryRepository.findAllPricePerCategories();
        return results.stream()
                .map(row -> new PricePerCategoryResponse(
                        (Integer) row[0],
                        (String) row[1],
                        (String) row[2],
                        new BigDecimal(String.valueOf(row[3]))
                )).toList();
    }

    private ResourceNotFoundException getResourceNotFoundException(Integer id) {
        return new ResourceNotFoundException("Category not found with ID: " + id);
    }
}
