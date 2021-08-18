package br.com.alura.mvc.mudi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<ProductOrder> orders;
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

