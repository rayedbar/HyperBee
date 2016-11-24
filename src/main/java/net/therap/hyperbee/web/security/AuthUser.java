package net.therap.hyperbee.web.security;

import net.therap.hyperbee.domain.Role;

import java.io.Serializable;
import java.util.List;

/**
 * @author rayed
 * @since 11/23/16 1:56 PM
 */

public class AuthUser implements Serializable{

    private static final long serialVersionUID = 1;

    private int id;
    private String username;
    private List<Role> roleList;

    public AuthUser(int id, String username, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.roleList = roleList;
    }

    public AuthUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoleList() {

        return this.roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}