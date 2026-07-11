package productos_jumanji.ms.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productos_jumanji.ms.enums.Categoria;

@Entity
@Table(name="producto")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre", nullable=false, length=100, unique=true)
    private String nombre;

    @Column(name="imagen", nullable=false, length=100)
    private String imagen;

    @Column(name="descripcion", nullable=false, length=255)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

    @Column(name="precio", nullable=false)
    private Integer precio;

    @Column(name="descuento", nullable=false)
    private Double descuento;

    @Column(name="precio_final", nullable=true)
    private Integer precioFinal;

    @Column(name="cant_jugadores", nullable=false, length=50)
    private String cantidadJugadores;

    @Column(name="duracion", nullable=false, length=50)
    private String duracion;

    @Column(name="stock", nullable=false)
    private Integer stock;
    
    @Column(name="dificultad", nullable=false)
    private Integer dificultad;

    @Column(name="esta_disponible", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean estaDisponible;

    @Column(name="creado_at")
    private LocalDateTime creadoAt;

    @Column(name="modificado_at")
    private LocalDateTime modificadoAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estadisticas_id", nullable=false, unique=true)
    private EstadisticasVentas estadisticasVentas;

    private void recalcularPrecioFinal() {
        this.precioFinal = (int) Math.round(
            this.precio - (this.precio * this.descuento)
        );
    }

    @PrePersist
    public void prePersist(){

        this.creadoAt = LocalDateTime.now();

        this.estaDisponible = true;

        this.precioFinal = (int) Math.round(
            this.precio - (this.precio * this.descuento)
        );
    }

    @PreUpdate
    public void preUpdate() {
        this.modificadoAt = LocalDateTime.now();
        this.recalcularPrecioFinal();
    }
}