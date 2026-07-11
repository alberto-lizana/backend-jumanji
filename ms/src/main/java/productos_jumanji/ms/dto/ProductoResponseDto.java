package productos_jumanji.ms.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productos_jumanji.ms.enums.Categoria;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProductoResponseDto {

    private Long id;
    private String nombre;
    private String imagen;
    private String descripcion;
    private Categoria categoria;
    private Integer precio;
    private Double descuento;
    private Integer precioFinal;
    private String cantidadJugadores;
    private String duracion;
    private Integer stock;
    private Integer dificultad;
    private boolean estaDisponible;
    private LocalDateTime creadoAt;
    private LocalDateTime modificadoAt;
    private EstadisticasResponseDto estadisticasVentas;

}
