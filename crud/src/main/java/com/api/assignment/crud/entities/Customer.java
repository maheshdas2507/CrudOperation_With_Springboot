package com.api.assignment.crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    public static Object existCus;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    
    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="street")
    private String  street;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(int id, String first_name, String last_name, String street, String address, String city,
            String state, String email, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street = street;
        this.address = address;
        this.city = city;
        this.state = state;
        this.email = email;
        this.phone = phone;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", street=" + street
                + ", address=" + address + ", city=" + city + ", state=" + state + ", email=" + email + ", phone="
                + phone + "]";
    }

    
    

    

   
    

    

}
