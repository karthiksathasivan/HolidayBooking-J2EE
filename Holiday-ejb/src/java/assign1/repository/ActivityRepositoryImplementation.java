/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository;

import assign1.repository.entities.Activity;
import assign1.repository.entities.ActivityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Karth
 */

@Stateless
public class ActivityRepositoryImplementation implements ActivityRepository{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void addActivity(Activity activity) throws Exception {
        entityManager.persist(activity);
    }

    @Override
    public Activity searchActivityById(int id) throws Exception {
        Activity activity = entityManager.find(Activity.class, id);  
        return activity;
    }

    @Override
    public List<Activity> getAllActivities() throws Exception {
        return entityManager.createNamedQuery(Activity.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeActivity(int activityId) throws Exception {
        Activity activity = this.searchActivityById(activityId);
      
        if (activity != null) {
            entityManager.remove(activity);
        }
    }

    @Override
    public void editActivity(Activity activity) throws Exception {
        entityManager.merge(activity);
    }
    
    @Override
    public List<Activity> searchActivityByType(ActivityType activityType) throws Exception {
        activityType = entityManager.find(ActivityType.class, activityType.getTypeID());
        activityType.getActivities().size();
        entityManager.refresh(activityType);
        
        List<Activity> searchActivities = new ArrayList<>();
        List<Activity> activities = getAllActivities();
        for(Activity act : activities)
        {
            if(act.getActivityType().equals(activityType))
                searchActivities.add(act);
        }
        return searchActivities;
    }

    @Override
    public List<ActivityType> getAllActivityType() throws Exception {
        return entityManager.createNamedQuery(ActivityType.GET_ALL_QUERY_NAME).getResultList();
    }
    
    @Override
    public List<Activity> searchActivityByName(String name) throws Exception {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Activity> c = qb.createQuery(Activity.class);
        Root<Activity> p = c.from(Activity.class);
        Predicate condition = qb.like(p.<String>get("activityName"), name.toUpperCase());
        c.where(condition);
        TypedQuery<Activity> q = entityManager.createQuery(c);
        List<Activity> result = q.getResultList();
        System.out.println(result);
        return result;
    }
}
