package com.example.soswedding.model;

 public class User {
     private long id;
     private String uuid;
     private String firstName;
     private String lastName;
     private String email;
     private String phoneNumber;
     private String serviceProvided;
     private String companyName;
     private String type;

     public User() {
         firstName   = "";
         lastName    = "";
         email       = "";
         id          = 0;
         uuid        = "";
         phoneNumber = "";
         type        = "";
         companyName = "";
     }

     public User(long id, String uuid, String firstName, String lastName, String email,String type, String phoneNumber) {
         this.id          = id;
         this.uuid        = uuid;
         this.firstName   = firstName;
         this.lastName    = lastName;
         this.email       = email;
         this.phoneNumber = phoneNumber;
         this.type        = type;
         companyName      = " ;";
     }

     public String getServiceProvided() {
         return serviceProvided;
     }
     public void setServiceProvided(String serviceProvided) { this.serviceProvided = serviceProvided; }

     public String getCompanyName() {
         return companyName;
     }
     public void setCompanyName(String companyName) {
         this.companyName = companyName;
     }

     public String getType() {
         return type;
     }
     public void setType(String type) {
         this.type = type;
     }

     public long getId() {
         return id;
     }
     public void setId(long id) {
         this.id = id;
     }

     public String getUuid() {
         return uuid;
     }
     public void setUuid(String uuid) {
         this.uuid = uuid;
     }

     public String getFirstName() {
         return firstName;
     }
     public void setFirstName(String firstName) {
         this.firstName = firstName;
     }

     public String getLastName() {
         return lastName;
     }
     public void setLastName(String lastName) {
         this.lastName = lastName;
     }

     public String getEmail() {
         return email;
     }
     public void setEmail(String email) {
         this.email = email;
     }

     public String getPhoneNumber() {
         return phoneNumber;
     }
     public void setPhoneNumber(String phoneNumber) {
         this.phoneNumber = phoneNumber;
     }
}
