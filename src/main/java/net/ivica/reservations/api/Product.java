package net.ivica.reservations.api;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;

@NamedQueries({
        @NamedQuery(name = "find_product_by_name", query = "select p from Product p where p.productName like :productName"),
        @NamedQuery(name = "find_all_products", query = "select p from Product p")})
@Entity
@BatchSize(size = 100)
@Table(name = "product")
public class Product implements Identifiable {

    public static volatile SingularAttribute<Product, Long> productId;
    public static volatile SingularAttribute<Product, String> productDescription;

    private Long _productId;

    private String _productName;
    private String _productDescription;

    @Transient
    @Override
    public Long getIdentifier() {
        return _productId;
    }

    @Column(name = "product_description", length = 255, nullable = false)
    public String getProductDescription() {
        return _productDescription;
    }

    public void setProductDescription(String productDescription) {
        _productDescription = productDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public Long getProductId() {
        return _productId;
    }

    public void setProductId(Long productId) {
        _productId = productId;
    }

    @Column(name = "product_name", length = 100, nullable = false)
    public String getProductName() {
        return _productName;
    }

    public void setProductName(String productName) {
        _productName = productName;
    }

}