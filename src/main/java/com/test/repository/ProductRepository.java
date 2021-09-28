package com.test.repository;

import com.test.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface which is used to
 *
 * @author Tarun Rohila
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
