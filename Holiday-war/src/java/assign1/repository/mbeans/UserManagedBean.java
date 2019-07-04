/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.mbeans;

import assign1.repository.SysUserRepository;
import assign1.repository.entities.Address;
import assign1.repository.entities.SysUser;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Karth
 */
@Named(value = "userManagedBean")
@RequestScoped
public class UserManagedBean implements Serializable {

    @EJB
    private SysUserRepository userHandler;
    private SysUser sysUser;
    private Address address;
    private List<SysUser> sysUsers;
    private int userId;
    
    public UserManagedBean() {
        this.sysUser = new SysUser();
        this.sysUsers = new ArrayList<>();
        this.address = new Address();
        this.userId = 0;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
   
    public SysUserRepository getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(SysUserRepository userHandler) {
        this.userHandler = userHandler;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
   
    @PostConstruct
    public void init(){
        try {
            this.sysUsers = this.userHandler.getAllSysUsers();
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sysUser = new SysUser();
        this.address = new Address();
    }
    
    public void searchById(int id) throws Exception {
        this.sysUsers.clear();
        this.sysUser = this.userHandler.searchSysUserById(id);
        this.address = this.sysUser.getAddress();
        this.sysUsers.add(this.sysUser);
        this.userId = id;
    }
    
    public void addUser() throws Exception{
        this.sysUser.setAddress(this.address);
        this.userHandler.addSysUser(this.sysUser);  
        init();
    }
    
    public void editSysUser() throws Exception{
        this.sysUser.setUserID(this.userId);
        this.sysUser.setAddress(this.address);
        this.userHandler.editSysUser(this.sysUser);
        init();
    }
    
    public void removeUser(int sysUserID) throws Exception{
        this.userHandler.removeSysUser(sysUserID);
        init();
    }
}

