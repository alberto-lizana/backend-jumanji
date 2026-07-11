package productos_jumanji.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EstadisticasResponseDto {

    private Long id;
    private Integer unidadesVendidas;
    private Integer devoluciones;
    private Integer ratingPromedio;
}
