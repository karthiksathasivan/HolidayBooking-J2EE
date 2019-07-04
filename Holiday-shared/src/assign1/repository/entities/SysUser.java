/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Karth
 */
@Entity
@Table(name = "SYS_USER")
@NamedQueries({@NamedQuery(name = SysUser.GET_ALL_QUERY_NAME, query = "SELECT c FROM SysUser c")})
public class SysUser implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "SysUser.getAll";
   
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userType;
    private Address address;
    private String contact;

    private Set <UserActivity> userActivity;

  
    public SysUser() {
    }

    public SysUser(int userID, String firstName, String lastName, String email, 
            String password, String userType, Address address, String contact) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.address = address;
        this.contact = contact;
        this.userActivity = new HashSet<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "usertype")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }   
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.userID;
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.contact);
        return hash;
    }

    @OneToMany(mappedBy="sysUser", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<UserActivity> getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(Set<UserActivity> userActivity) {
        this.userActivity = userActivity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SysUser other = (SysUser) obj;
        if (this.userID != other.userID) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }
       
    @Override
    public String toString() {
        return "SysUser{" + "userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", userType=" + userType + ", address=" + address + ", contact=" + contact + '}';
    }
    
}
