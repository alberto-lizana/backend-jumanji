package productos_jumanji.ms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="estadisticas")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EstadisticasVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="unidades_vendidas", nullable=true)
    private Integer unidadesVendidas;

    @Column(name="devoluciones", nullable=true)
    private Integer devoluciones;

    @Column(name="rating_promedio", nullable=true)
    private Integer ratingPromedio;

    @OneToOne(mappedBy = "estadisticasVentas")
    private Producto producto;

    @PrePersist
    private void prePersist(){
        this.unidadesVendidas = null;
        this.devoluciones = null;
        this.ratingPromedio = null;
    }
}
