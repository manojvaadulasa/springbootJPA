package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //  The below is called as a JPQL query where we use the class name Product to retrieve data
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

    //  Now we use NativeSQL Query where we use the actual table name instead of the class name
    @Query(
        value = "SELECT * FROM products p WHERE " +
        "p.name LIKE CONCAT('%',:query,'%')" +
        "Or p.description LIKE CONCAT('%',:query,'%')",
        nativeQuery = true
    )
    List<Product> searchProductsSQL(String query);
}
