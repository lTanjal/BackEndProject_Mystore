package backendproject.mystore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmId", nullable = false, updatable = false)
    private Long cmId;

    // Username with unique constraint
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "cmFirstName", nullable = false)
    private String cmFirstName;

    @Column(name = "cmLastName", nullable = false)
    private String cmLastName;

    @Column(name = "cmEmail", nullable = false, unique = true)
    private String cmEmail;

    @Column(name = "password", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
	private List<Product> products;

    public AppUser() {
    }

    public AppUser(String userName, String cmFirstName, String cmLastName, String cmEmail, String passwordHash,
            String role) {
        super();
        this.userName = userName;
        this.cmFirstName = cmFirstName;
        this.cmLastName = cmLastName;
        this.cmEmail = cmEmail;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getCmId() {
        return cmId;
    }

    public void setCmId(Long cmId) {
        this.cmId = cmId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCmFirstName() {
        return cmFirstName;
    }

    public void setCmFirstName(String cmFirstName) {
        this.cmFirstName = cmFirstName;
    }

    public String getCmLastName() {
        return cmLastName;
    }

    public void setCmLastName(String cmLastName) {
        this.cmLastName = cmLastName;
    }

    public String getCmEmail() {
        return cmEmail;
    }

    public void setCmEmail(String cmEmail) {
        this.cmEmail = cmEmail;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
