/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.mbeans;

import assign1.repository.ActivityRepository;
import assign1.repository.entities.Activity;
import assign1.repository.entities.ActivityType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Karth
 */
@Named("activityManagedBean")
@ApplicationScoped
public class ActivityManagedBean implements Serializable {

    @EJB
    private ActivityRepository activityHandler;
    private Activity activity;
    private ActivityType activityType;
    private List<Activity> activities;
    private List<ActivityType> activityTypes;

    public ActivityManagedBean() {
        this.activities = new ArrayList<>();
        this.activity = new Activity();
        this.activityTypes = new ArrayList<>(); 
        this.activityType = new ActivityType();
        
    }   

    @PostConstruct
    public void init(){
        try {
            this.activities = this.activityHandler.getAllActivities();
            this.activityTypes = this.activityHandler.getAllActivityType();
        } catch (Exception ex) {
            Logger.getLogger(ActivityManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.activity = new Activity();
    }
    
    public List<ActivityType> getActivityTypes() {
        return activityTypes;
    }

    public void setActivityTypes(List<ActivityType> activityTypes) {
        this.activityTypes = activityTypes;
    }

    
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public void searchById() throws Exception {
        this.activities.clear();
        this.activities.add(this.activityHandler.searchActivityById(this.activity.getActivityId()));
    }
    
    public void searchByName() throws Exception {
        this.activities.clear();
        this.activities = this.activityHandler.searchActivityByName(this.activity.getActivityName());
    }

     public void searchByType() throws Exception {
        this.activities.clear();
        System.out.println(this.activityType);
        this.activities = this.activityHandler.searchActivityByType(this.activityType);
    } 
}
