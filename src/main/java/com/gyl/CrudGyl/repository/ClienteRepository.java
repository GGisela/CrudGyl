package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByApellido(String apellido);


}
