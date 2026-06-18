package com.paco.catalog_customers.repository;

import com.paco.catalog_customers.entity.B4bCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface B4bCustomerRepository extends JpaRepository<B4bCustomer, Long> {
    // Permite listar opcionalmente solo los clientes activos
    List<B4bCustomer> findByStatus(String status);
}