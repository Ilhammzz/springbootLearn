package com.indocyber.jpa.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //Derived Query
    List<Product> findByCategoryNameAndDiscontinueFalse(String categoryName);

    // JPQL (entity-based join)
    @Query("""
                SELECT p FROM Product p
                    JOIN p.category c
                WHERE c.name = :categoryName AND p.discontinue = false
            """)
    List<Product> findActiveProductsByCategoryName(String categoryName);

    // Native SQL (table/column-based join)
    @Query(value = """
            SELECT p.* FROM Products p
                JOIN Categories c ON p.CategoryId = c.Id
            WHERE c.Name = :categoryName AND p.Discontinue = 0
            """,
            nativeQuery = true)
    List<Product> findActiveProductsByCategoryNameNative(String categoryName);

    @Procedure(procedureName = "GetProductsByCategory")
    List<Product> getProductsByCategory(String categoryName);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Product p
            SET p.unitsInStock = p.unitsInStock + p.onOrder, p.onOrder = 0
            WHERE p.id = :productId AND p.discontinue = false
            """)
    int exchangeStockFromOnOrder(Integer productId);

    @Procedure(procedureName = "ExchangeStockOnOrder")
    void callExchangeStockOnOrder(Integer productId);

    // Bulk Update ---
    // Reset all discontinued products' unitsInStock to 0
    @Modifying
    @Transactional
    @Query("""
            UPDATE Product p
            SET p.unitsInStock = 0
            WHERE p.discontinue = true
            """)
    int clearStockForDiscontinued();

}
