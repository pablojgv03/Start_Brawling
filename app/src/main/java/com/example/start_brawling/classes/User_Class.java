package com.example.start_brawling.classes;

public class User_Class {
    int id;
    String Name, Surname, UserName, Password;

    public User_Class(){
    }

    public User_Class(String name, String surname, String userName, String password) {
        Name = name;
        Surname = surname;
        UserName = userName;
        Password = password;
    }

    public boolean isNull(){
        if(Name.equals("")|| Surname.equals("")|| UserName.equals("") || Password.equals("")){
            return false;
        }else{
            return true;
        }
    }
    //toString mteod
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
