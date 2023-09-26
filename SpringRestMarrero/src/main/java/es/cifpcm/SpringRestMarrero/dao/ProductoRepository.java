package es.cifpcm.SpringRestMarrero.dao;

import es.cifpcm.SpringRestMarrero.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Override
    Optional<Producto> findById(Integer integer);

}