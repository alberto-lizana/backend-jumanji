package productos_jumanji.ms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import productos_jumanji.ms.dto.ProductoResponseDto;
import productos_jumanji.ms.enums.Categoria;
import productos_jumanji.ms.dto.ProductoRequestDinamicoDto;
import productos_jumanji.ms.dto.ProductoRequestDto;
import productos_jumanji.ms.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = {
    "http://localhost:4200",
    "https://front-end-angular-production.up.railway.app"
})
public class ProductoController {

    private final ProductoService productoService;

    ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @GetMapping()
    ResponseEntity<List<ProductoResponseDto>> obtenerTodosLosProductos(){
        List<ProductoResponseDto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/prod-cat")
    ResponseEntity<List<ProductoResponseDto>> obtenerProductosPorCategoria(@RequestParam Categoria categoria){
        List<ProductoResponseDto> productoCat = productoService.obtenerProductosCategoria(categoria);
        return ResponseEntity.ok(productoCat);
    }

    @PostMapping()
    public ResponseEntity<Optional<ProductoResponseDto>> crearProducto(@Valid @RequestBody ProductoRequestDto dto){
        Optional<ProductoResponseDto> nuevoProducto = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> modificar(@Valid @RequestBody ProductoRequestDinamicoDto dto, @PathVariable Long id){
        ProductoResponseDto nuevoProducto = this.productoService.modificarProducto(dto, id);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Borrado Lógico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borradoLogicoProducto(@PathVariable Long id) {
        productoService.borradoLogicoProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Borrado Físico
    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<Void> borradoFisicoProducto(@PathVariable Long id) {
        productoService.borradoFisicoProducto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
