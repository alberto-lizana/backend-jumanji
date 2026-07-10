package productos_jumanji.ms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import productos_jumanji.ms.enums.Categoria;
import productos_jumanji.ms.model.Producto;

@Repository
public interface ProductoRepository extends  JpaRepository<Producto, Long>{

    @Query("""
            SELECT p
            FROM Producto p
            WHERE p.id = :id
            """)
    Optional<Producto> findProductoById(@Param("id") Long id);

    @Query("""
            SELECT p
            FROM Producto p
            """)
    List<Producto> findAllProductos();

    @Query("""
            SELECT 
                p
            FROM Producto p
            WHERE p.categoria = :categoria
            """)
    List<Producto> findAllProductosCategoria(@Param("categoria") Categoria categoria);

    @Modifying
    @Query("""
        UPDATE Producto p
        SET p.estaDisponible = false
        WHERE p.id = :id
        """)
    int borradoLogico(@Param("id") Long id);

    @Modifying
    @Query("""
            DELETE FROM Producto p
            WHERE p.id = :id
            """)
    Integer borradoFisicoProducto(@Param("id") Long id);

    @Query("""
        SELECT p
        FROM Producto p
        WHERE LOWER(p.nombre) = LOWER(:nombre)
        """)
    Optional<Producto> buscarPorNombre(@Param("nombre") String nombre);
}
