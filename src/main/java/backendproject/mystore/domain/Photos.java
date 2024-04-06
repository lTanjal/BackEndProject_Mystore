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
    private Product product;

    public Photos() {
    }

    public Photos(String photoLink, Product product) {
        this.photoLink = photoLink;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString (){
        return "Photo links [photoId= "+photoId + ", Link "+ photoLink + ", product=" + (product != null ? product.getId() : "null") + "]";
    }

}
