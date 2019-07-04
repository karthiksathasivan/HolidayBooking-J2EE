/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository;

import assign1.repository.entities.SysUser;
import assign1.repository.entities.UserActivity;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Karth
 */
public interface SysUserRepository {
    
    public void addSysUser(SysUser SysUser) throws Exception;
    
    public void removeSysUser(int SysUserId) throws Exception;
    
    public void editSysUser(SysUser SysUser) throws Exception;
    
    public SysUser searchSysUserById(int id) throws Exception;
    
    public List<SysUser> searchSysUserByFirstName(String firstName) throws Exception;
      
    public List<SysUser> searchSysUserByType(String sysUserType) throws Exception;
   
    public List<SysUser> getAllSysUsers() throws Exception;
    
    public Set<UserActivity> getAllUserActivity(int userID) throws Exception;
    
    public void addUserActivity(UserActivity userActivity) throws Exception;
    
    public Set<UserActivity> cancelActivity(int activityID, int userID) throws Exception;
}
