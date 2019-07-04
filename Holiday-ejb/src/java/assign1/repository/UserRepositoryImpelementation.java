/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository;

import assign1.repository.entities.Activity;
import assign1.repository.entities.SysUser;
import assign1.repository.entities.UserActivity;
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
public class UserRepositoryImpelementation implements SysUserRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void addSysUser(SysUser sysUser) throws Exception {
        entityManager.persist(sysUser);
    }

    @Override
    public SysUser searchSysUserById(int id) throws Exception {
        SysUser sysUser = entityManager.find(SysUser.class, id);  
        return sysUser;    
    }

    @Override
    public void removeSysUser(int sysUserId) throws Exception {
        SysUser sysUser = this.searchSysUserById(sysUserId);
      
        if (sysUser != null) {
            entityManager.remove(sysUser);
        }
    }

    @Override
    public void editSysUser(SysUser sysUser) throws Exception {
        entityManager.merge(sysUser);
    }

    @Override
    public List<SysUser> searchSysUserByType(String sysUserType) throws Exception {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SysUser> c = qb.createQuery(SysUser.class);
        Root<SysUser> p = c.from(SysUser.class);
        Predicate condition = qb.like(p.<String>get("userType"), sysUserType.toUpperCase());
        c.where(condition);
        TypedQuery<SysUser> q = entityManager.createQuery(c);
        List<SysUser> result = q.getResultList();
        System.out.println(result);
        return result;
    }

    @Override
    public List<SysUser> searchSysUserByFirstName(String firstName) throws Exception {
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SysUser> c = qb.createQuery(SysUser.class);
        Root<SysUser> p = c.from(SysUser.class);
        Predicate condition = qb.like(p.<String>get("firstName"), firstName.toUpperCase());
        c.where(condition);
        TypedQuery<SysUser> q = entityManager.createQuery(c);
        List<SysUser> result = q.getResultList();
        System.out.println(result);
        return result;
    }

    @Override
    public List<SysUser> getAllSysUsers() throws Exception {
        return entityManager.createNamedQuery(SysUser.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public Set<UserActivity> getAllUserActivity(int id) throws Exception {
        SysUser sysUser = entityManager.getReference(SysUser.class, id);          
        return sysUser.getUserActivity(); 
    }

    @Override
    public void addUserActivity(UserActivity userActivity) throws Exception {  
        entityManager.persist(userActivity); 
        SysUser sysUser = entityManager.getReference(SysUser.class, userActivity.getSysUser().getUserID());
        Activity activity = entityManager.getReference(Activity.class, userActivity.getActivity().getActivityId());
        entityManager.merge(sysUser);
        entityManager.merge(activity); 
        entityManager.refresh(activity);
        entityManager.refresh(sysUser);
    }

    @Override
    public Set<UserActivity> cancelActivity(int activityID, int userID) throws Exception {
        entityManager.getEntityManagerFactory().getCache().evictAll();
        SysUser sysUser = entityManager.getReference(SysUser.class, userID);
        
        for (UserActivity temp : sysUser.getUserActivity()) {
            if (temp.getActivity().getActivityId() == activityID) {
                   entityManager.merge(sysUser);
                   entityManager.remove(temp);
            }
        }        
        entityManager.refresh(sysUser);       
        return sysUser.getUserActivity();
    }

}
