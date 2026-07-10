package productos_jumanji.ms.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import productos_jumanji.ms.dto.ProductoRequestDto;
import productos_jumanji.ms.dto.ProductoResponseDto;
import productos_jumanji.ms.enums.Categoria;
import productos_jumanji.ms.exception.ProductoExistenteException;
import productos_jumanji.ms.exception.ProductoNoEncontrado;
import productos_jumanji.ms.model.Producto;
import productos_jumanji.ms.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> obtenerTodosLosProductos() {
        List<Producto> prod = this.productoRepository.findAllProductos();
        return prod.stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
        }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDto> obtenerProductosCategoria(Categoria categoria){
        List<Producto> prod = this.productoRepository.findAllProductosCategoria(categoria);
        return prod.stream()
            .map(this::toResponseDto)
            .collect(Collectors.toList());
    }
    
    
    @Transactional
    @Override
    public Optional<ProductoResponseDto> crearProducto(ProductoRequestDto dto) {

        Optional<Producto> productoExistente =
                productoRepository.buscarPorNombre(dto.getNombre());

        productoExistente.ifPresent(p -> {
            throw new ProductoExistenteException(
                "Ya existe un producto con ese nombre"
            );
        });

        Producto nuevoProducto = Producto.builder()
                .nombre(dto.getNombre())
                .imagen(dto.getImagen())
                .descripcion(dto.getDescripcion())
                .categoria(dto.getCategoria())
                .precio(dto.getPrecio())
                .descuento(dto.getDescuento())
                .cantidadJugadores(dto.getCantidadJugadores())
                .duracion(dto.getDuracion())
                .stock(dto.getStock())
                .dificultad(dto.getDificultad())
                .build();

        Producto guardado = productoRepository.save(nuevoProducto);

        
        return Optional.of(toResponseDto(guardado));
    }

    @Transactional
    public void borradoLogicoProducto(Long id) {
        Producto producto = productoRepository.findProductoById(id)
                .orElseThrow(() -> new ProductoNoEncontrado("Producto no encontrado"));

        producto.setEstaDisponible(false);
        productoRepository.save(producto); 
    }

    @Transactional
    @Override
    public void borradoFisicoProducto(Long id) {
        int filasBorradas = this.productoRepository.borradoFisicoProducto(id);

        if (filasBorradas == 0) {
            throw new ProductoNoEncontrado("Producto no encontrado");
        }
    }


    private ProductoResponseDto toResponseDto(Producto producto) {
        return ProductoResponseDto.builder()
            .id(producto.getId())
            .nombre(producto.getNombre())
            .imagen(producto.getImagen())
            .descripcion(producto.getDescripcion())
            .categoria(producto.getCategoria())
            .precio(producto.getPrecio())
            .descuento(producto.getDescuento())
            .precioFinal(producto.getPrecioFinal())
            .cantidadJugadores(producto.getCantidadJugadores())
            .duracion(producto.getDuracion())
            .stock(producto.getStock())
            .dificultad(producto.getDificultad())
            .estaDisponible(producto.getEstaDisponible())
            .creadoAt(producto.getCreadoAt())
            .modificadoAt(producto.getModificadoAt())
            .build();
    }
}

