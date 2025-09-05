package com.revisiting.jpa.hibernate.spring_data_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name="product_table",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku_unique", columnNames = {"sku"}),
                @UniqueConstraint(name= "title_price_unique", columnNames = {"title", "price"})
        },
        indexes = {
                @Index(
                        name="sku_index", columnList= "sku"
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String sku;

    @Column
    private String title;

    @Column
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    BigDecimal price ;

    @Override
    public String toString() {
        return "\n{ " + "id: " + id + ",\nsku: " + sku + ",\n title: " + title +",\nquantity: " + quantity + ",\nprice: " + price + "\n}" ;
    }

}
