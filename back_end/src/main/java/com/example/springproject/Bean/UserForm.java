package com.example.springproject.Bean;

import com.example.springproject.domain.UserInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Deprecated
public class UserForm {
    @NotBlank(message = "Username shouldn't be null")
    private String username;
    @Length(min = 6, message = "Password need at least 6 bits")
    private String password;
    @NotBlank(message = "Confirm password shouldn't be null")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean checkPasswordEquals(){
        return this.password.equals(this.confirmPassword);
    }

    public UserInfo convertToUser(){
        return new UserInfo(username,password);
    }
}
