/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Karth
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Activity.GET_ALL_QUERY_NAME, query = "SELECT a FROM Activity a")})
public class Activity implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "Activity.getAll";
    
    private int activityId;
    private String activityName;
    private ActivityType activityType;
    private String activityDescription;
    
    private Set <UserActivity> userActivity;

    public Activity() {
      
    }

    public Activity(int activityId, String activityName, ActivityType activityType, 
            String activityDescription, SysUser sysUser) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityType = activityType;
        this.activityDescription = activityDescription;
    }

    @Id
    @Column(name = "activity_id")
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @ManyToOne
    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    @OneToMany(mappedBy="activity", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<UserActivity> getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(Set<UserActivity> userActivity) {
        this.userActivity = userActivity;
    }

    public String brief() {
        return "Activity{" + "activityId=" + activityId + ", activityName=" + activityName + '}';
    }
    
    @Override
    public String toString() {
        return "Activity{" + "activityId=" + activityId + ", activityName=" + activityName + ", activityType=" + activityType + ", activityDescription=" + activityDescription + '}';
    }
    
}
