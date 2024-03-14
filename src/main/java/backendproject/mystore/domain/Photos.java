package backendproject.mystore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long photoId;
    private String photoLink;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product products;

    public Photos() {
    }

    public Photos(String photoLink, Product products) {
        this.photoLink = photoLink;
        this.products = products;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    @Override
    public String toString (){
        return "Photo links [photoId= "+photoId + ", Link "+ photoLink + ", product="+ this.getProducts().getProductName()+"]";
    }

}
