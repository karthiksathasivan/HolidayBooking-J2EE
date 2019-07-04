/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Karth
 */
@Entity
@Table(name = "ACT_TYPE")
@NamedQueries({@NamedQuery(name = ActivityType.GET_ALL_QUERY_NAME, query = "SELECT a FROM ActivityType a")})
public class ActivityType implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "ActivityType.getAll";
    
    private int typeID;
    private String typeName;

    private Set<Activity> activities;
    
    public ActivityType() {
    }

    public ActivityType(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
        this.activities = new HashSet<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "type_id")
    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @OneToMany(mappedBy = "activityType" ,fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.typeID;
        return hash;
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
        final ActivityType other = (ActivityType) obj;
        if (this.typeID != other.typeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ActivityType{" + "typeID=" + typeID + ", typeName=" + typeName + ", activities=" + activities + '}';
    }

    
       
}
