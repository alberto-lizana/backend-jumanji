package productos_jumanji.ms.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ProductoRequestDto {

    @NotBlank(message= "El nombre es obligatorio")
    private String nombre;

    private String imagen;

    @NotBlank(message= "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message= "La categoría es obligatoria")
    private Categoria categoria;

    @NotNull(message= "El precio es obligatorio")
    @Positive(message= "El precio tiene que un número natural")
    private Integer precio;

    @NotNull(message= "El descuento es obligatorio")
    @DecimalMin(value= "0.0")
    @DecimalMax(value= "1.0")
    private Double descuento;

    @NotBlank(message = "La cantidad de jugadores es obligatoria")
    private String cantidadJugadores;

    @NotBlank(message= "la duración es obligatoria")
    private String duracion;

    @NotNull(message= "El stock es obligatorio")
    @Min(value=1)
    private Integer stock;
    
    @NotNull(message= "La dificultad es obligatoria")
    @Min(value=1, message= "La dificultad mínima es de 1")
    @Max(value=5, message= "La dificultad máxima es de 5")
    private Integer dificultad;

}
