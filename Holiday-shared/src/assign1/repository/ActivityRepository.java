/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository;

import assign1.repository.entities.Activity;
import assign1.repository.entities.ActivityType;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Karth
 */
@Remote
public interface ActivityRepository {
    
    public void addActivity(Activity activity) throws Exception;
    
    public Activity searchActivityById(int id) throws Exception;
    
    public List<Activity> getAllActivities() throws Exception;
    
    public void removeActivity(int activityId) throws Exception;
    
    public void editActivity(Activity activity) throws Exception;
    
    public List<Activity> searchActivityByType(ActivityType activityType) throws Exception;
    
    public List<ActivityType> getAllActivityType() throws Exception; 
    
    public List<Activity> searchActivityByName(String name) throws Exception;
                
}
