package com.paco.catalog_customers.controller;

import com.paco.catalog_customers.dto.LoginRequest;
import com.paco.catalog_customers.dto.LoginResponse;
import com.paco.catalog_customers.repository.AppUserRepository;
import com.paco.catalog_customers.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de los procesos de autenticación y autorización de usuarios.
 * Proporciona el punto de acceso (endpoint) público para la generación de tokens JWT.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    /**
     * Autentica un usuario en el sistema utilizando sus credenciales básicas.
     * Si la validación de Spring Security es exitosa, genera y retorna un token JWT válido.
     *
     * @param request DTO que contiene el username y password provistos por el cliente.
     * @return ResponseEntity con el token JWT (200 OK) o un mensaje de error detallado (401 Unauthorized).
     */
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.Operation(
        summary = "Autenticar usuario",
        description = "Recibe las credenciales del usuario (username y password), las valida contra la base de datos y retorna un token JWT válido.",
        security = {} // Indica explícitamente que NO requiere Token para ser ejecutado
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Autenticación exitosa. Devuelve el token JWT.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Credenciales incorrectas o usuario deshabilitado.")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            // 1. Delegar la validación de credenciales al AuthenticationManager (compara contra Postgres usando BCrypt)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            
            // 2. Si no se lanzó ninguna excepción, el usuario es válido; procedemos a cargar sus detalles
            final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
            
            // 3. Generar el token firmado con los claims del usuario
            final String jwt = jwtService.generateToken(user);
            
            // 4. Retornar la respuesta exitosa
            return ResponseEntity.ok(new LoginResponse(jwt));
            
        } catch (BadCredentialsException e) {
            // Manejo controlado del error de credenciales incorrectas para evitar el comportamiento por defecto (403 vacío)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: Credenciales incorrectas.");
        } catch (org.springframework.security.core.AuthenticationException e) {
            // Captura otros problemas específicos de Spring Security (ej. usuario deshabilitado)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: " + e.getMessage());
        } catch (Exception e) {
            // Captura cualquier otro fallo imprevisto del sistema
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor al procesar la solicitud.");
        }
    }
}