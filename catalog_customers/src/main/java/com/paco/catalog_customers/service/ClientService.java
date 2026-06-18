package com.paco.catalog_customers.service;

import com.paco.catalog_customers.entity.B4bCustomer;
import com.paco.catalog_customers.repository.B4bCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final B4bCustomerRepository customerRepository;

    public List<B4bCustomer> getAllClients() {
        return customerRepository.findAll();
    }

    public B4bCustomer getClientById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public B4bCustomer createClient(B4bCustomer customer) {
        return customerRepository.save(customer);
    }

    public B4bCustomer updateClient(Long id, B4bCustomer clientDetails) {
        B4bCustomer customer = getClientById(id);
        
        customer.setBusinessName(clientDetails.getBusinessName());
        customer.setCommercialName(clientDetails.getCommercialName());
        customer.setEmail(clientDetails.getEmail());
        customer.setPhone(clientDetails.getPhone());
        customer.setCreditLimit(clientDetails.getCreditLimit());
        customer.setStatus(clientDetails.getStatus());
        
        return customerRepository.save(customer);
    }

    public void deleteOrDeactivateClient(Long id) {
        B4bCustomer customer = getClientById(id);
        // Desactivación lógica (Soft Delete) recomendada por el diseño del script
        customer.setStatus("INACTIVE");
        customerRepository.save(customer);
    }
    
}