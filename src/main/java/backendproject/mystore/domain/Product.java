package backendproject.mystore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private Integer quantity;
    private double price;
    private String size;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "productStatus")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private AppUser appUser;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Photos> photos;

    public Product() {
    }

    public Product(String productName, Integer quantity, double price, String size, String comments, AppUser appUser, Status status) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.size=size;
        this.comments=comments;
        this.appUser = appUser;
        this.status = status;

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {

        return "Product [id=" + id + ", Product name=" + productName + ", quantity=" + quantity + ", price=" + price
                + ", size="+size+", comments="+comments+", owner= " + this.getAppUser().getCmFirstName() + ", status " + this.getStatus()+" ]";
    }

    
}
