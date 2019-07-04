    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Karth
 */
@Entity
@Table(name = "userActivity")

public class UserActivity implements Serializable{
    
    
    @Id        
    @ManyToOne
    @JoinColumn(name = "sysuser_userID")
    private SysUser sysUser;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "activity_activityID")
    private Activity activity;
    
    @Column(name = "activityStatus")
    private String activityStatus;
    
    @Column(name = "bookingDate")
    private String bookingDate;

    public UserActivity() {
    }

    public UserActivity(SysUser sysUser, Activity activity, String activityStatus, String bookingDate) {
        this.sysUser = sysUser;
        this.activity = activity;
        this.activityStatus = activityStatus;
        this.bookingDate = bookingDate;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.sysUser);
        hash = 83 * hash + Objects.hashCode(this.activity);
        hash = 83 * hash + Objects.hashCode(this.activityStatus);
        hash = 83 * hash + Objects.hashCode(this.bookingDate);
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
        final UserActivity other = (UserActivity) obj;
        if (!Objects.equals(this.activityStatus, other.activityStatus)) {
            return false;
        }
        if (!Objects.equals(this.bookingDate, other.bookingDate)) {
            return false;
        }
        if (!Objects.equals(this.sysUser, other.sysUser)) {
            return false;
        }
        if (!Objects.equals(this.activity, other.activity)) {
            return false;
        }
        return true;
    }

   
    
    
}
