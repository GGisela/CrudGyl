package com.gyl.CrudGyl.repository;
import com.gyl.CrudGyl.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository <Venta, Long> {
    // Aquí podrías agregar métodos personalizados, por ejemplo:
    // List<Venta> findByClienteId(Long idCliente);
}
