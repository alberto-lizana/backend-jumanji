package productos_jumanji.ms.service;

import java.util.List;
import java.util.Optional;

import productos_jumanji.ms.dto.ProductoRequestDto;
import productos_jumanji.ms.dto.ProductoResponseDto;
import productos_jumanji.ms.enums.Categoria;

public interface ProductoService {

    List<ProductoResponseDto> obtenerTodosLosProductos();
    List<ProductoResponseDto> obtenerProductosCategoria(Categoria categoria);
    void borradoLogicoProducto(Long id);
    void borradoFisicoProducto(Long id);
    Optional<ProductoResponseDto> crearProducto(ProductoRequestDto dto);
}
