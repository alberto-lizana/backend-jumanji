package productos_jumanji.ms.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productos_jumanji.ms.enums.Categoria;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProductoRequestDinamicoDto {

    @Size(max = 100, message="Largo máximo 100 caracteres")
    private String nombre;

    @Size(max = 100, message="Largo máximo 100 caracteres")
    private String imagen;

    @Size(max = 255, message="Largo máximo 255 caracteres")
    private String descripcion;
    private Categoria categoria;

    @Positive(message= "El precio tiene que un número natural")
    private Integer precio;

    @DecimalMin("0.0")
    @DecimalMax("1.0")
    private Double descuento;

    @Size(max = 50, message="Largo máximo 50 caracteres")
    private String cantidadJugadores;

    @Size(max = 50, message="Largo máximo 50 caracteres")
    private String duracion;
    private Integer stock;

    @Min(value=1, message= "La dificultad mínima es de 1")
    @Max(value=5, message= "La dificultad máxima es de 5")
    private Integer dificultad;

    @PositiveOrZero
    private Integer unidadesVendidas;

    @PositiveOrZero
    private Integer devoluciones;

    @Min(value=1, message= "La rating promedio mínima es de 1")
    @Max(value=5, message= "La rating promedio máxima es de 5")
    @Positive(message= "El rating promedio tiene que ser un número positivo")
    private Integer ratingPromedio;

}
