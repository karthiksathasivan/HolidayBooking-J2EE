/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.mbeans;

import assign1.repository.ActivityRepository;
import assign1.repository.SysUserRepository;
import assign1.repository.entities.Activity;
import assign1.repository.entities.SysUser;
import assign1.repository.entities.UserActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Karth
 */
@Named(value = "userActivityManagedBean")
@SessionScoped
public class UserActivityManagedBean implements Serializable{
    @EJB
    private SysUserRepository userHandler;
    @EJB
    private ActivityRepository activityHandler;
    private SysUser user;
    private Activity activity;
    private List<Activity> activities;
    private List<UserActivity> userActivities;
    private UserActivity userActivity;
    private int id;
    /**
     * Creates a new instance of UserActivityManagedBean
     */
    public UserActivityManagedBean() {
        this.activity = new Activity();
        this.activities = new ArrayList<>();
        this.user = new SysUser();
        this.userActivities = new ArrayList<>();
        this.userActivity = new UserActivity();                
    }
    
    @PostConstruct    
    public void init(){
        try {
            this.activities = this.activityHandler.getAllActivities();
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ActivityRepository getActivityHandler() {
        return activityHandler;
    }

    public void setActivityHandler(ActivityRepository activityHandler) {
        this.activityHandler = activityHandler;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public SysUserRepository getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(SysUserRepository userHandler) {
        this.userHandler = userHandler;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

   

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }

    public UserActivity getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(UserActivity userActivity) {
        this.userActivity = userActivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void searchById() throws Exception {
        this.userActivities.clear();
        try{
        this.userActivities.addAll(this.userHandler.getAllUserActivity(this.user.getUserID())); 
        }
        catch(Exception e){
            System.out.print("User Does not Exist");
        }
    }
    
    public void addActivity() throws Exception{
        try{
            
            this.userActivity = new UserActivity(this.user, this.activity, "Booked" , "");
            this.userHandler.addUserActivity(this.userActivity);
        }
        catch(Exception e){
            System.out.println("User Does not exist");
        }
       
    }
    
    public void cancelActivity(int activityID, int userID) throws Exception{
        this.userHandler.cancelActivity(activityID, userID);
        
    }
}
