package com.paco.catalog_customers.controller;

import com.paco.catalog_customers.entity.B4bCustomer;
import com.paco.catalog_customers.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Endpoints protegidos para la gestión del catálogo de clientes")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Operation(
        summary = "Listar clientes",
        description = "Retorna una lista completa de todos los clientes registrados en el sistema. Requiere token JWT en la cabecera."
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito.")
    public ResponseEntity<List<B4bCustomer>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener un cliente por ID",
        description = "Busca y retorna los detalles de un cliente específico utilizando su identificador único."
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente.")
    @ApiResponse(responseCode = "401", description = "No autorizado.")
    @ApiResponse(responseCode = "404", description = "El cliente con el ID suministrado no existe en la base de datos.")
    public ResponseEntity<?> getClientById(
            @Parameter(description = "ID del cliente a buscar", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @Operation(
        summary = "Crea un cliente",
        description = "Registra un nuevo cliente en el sistema. Realiza validaciones automáticas como la estructura del RUC/Cédula y que el código JDE no esté duplicado."
    )
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente.")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o violaciones de restricciones (ej. RUC duplicado).")
    @ApiResponse(responseCode = "401", description = "No autorizado.")
    public ResponseEntity<B4bCustomer> createClient(@RequestBody B4bCustomer customer) {
        return ResponseEntity.ok(clientService.createClient(customer));
    }

    @SuppressWarnings("unused")
	@PutMapping("/{id}")
    @Operation(
        summary = "Actualizar cliente existente",
        description = "Actualiza la información completa de un cliente mediante su ID. " +
                      "El cuerpo de la petición debe contener todos los campos necesarios. " +
                      "Si el ID no existe, retornará un error 404."
    )
    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente.")
    @ApiResponse(responseCode = "404", description = "No se encontró el cliente con el ID proporcionado.")
    public ResponseEntity<B4bCustomer> updateClient(
            @Parameter(description = "ID único del cliente", example = "1") @PathVariable Long id, 
            @RequestBody B4bCustomer customer) {
        return ResponseEntity.ok(clientService.updateClient(id, customer));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Elimina o desactiva un cliente",
        description = "Remueve un cliente del catálogo. Dependiendo de las políticas del negocio, esta operación realiza una desactivación de estado (borrado lógico a 'INACTIVE') para preservar la integridad referencial histórica."
    )
    @ApiResponse(responseCode = "200", description = "Cliente eliminado o desactivado correctamente.")
    @ApiResponse(responseCode = "401", description = "No autorizado.")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado.")
    public ResponseEntity<?> deleteClient(
            @Parameter(description = "ID del cliente a eliminar/desactivar", example = "1") @PathVariable Long id) {
        clientService.deleteOrDeactivateClient(id);
        return ResponseEntity.noContent().build();
    }
}