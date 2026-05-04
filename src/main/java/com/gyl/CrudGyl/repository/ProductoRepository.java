package com.gyl.CrudGyl.repository;

import com.gyl.CrudGyl.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Usa el service para listar prod activos
    List<Producto> findByActivoTrue();


    // lista productos Inactivos= false
    List<Producto> findByActivoFalse();
    List<Producto> findByNombre(String nombre);
    List<Producto> findByStock(Integer stock);




    //  !!futuras mejoras con filtros de busqueda
    //PLUS Búsqueda por nombre , Usamos 'Containing' para que busque coincidencias parciales (opcional)
    //List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Búsqueda por stock (ej para ver productos que se están agotando)
   // List<Producto> findByStock(Integer stock);
}
