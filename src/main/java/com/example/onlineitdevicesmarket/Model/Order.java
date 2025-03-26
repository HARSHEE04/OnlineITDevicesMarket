package com.example.onlineitdevicesmarket.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="orders")
public class Order {

    //to get existing users
    public Order(int id, User user, DeviceType deviceType, DeviceColor deviceColor, int quantity) {
        this.orderid = id;
        this.user = user;
        this.deviceType = deviceType;
        this.deviceColor = deviceColor;
        this.quantity = quantity;
        this.price = setPrice(deviceType);
    }

    public Order() {
    }

    public Order(User user, DeviceType deviceType, DeviceColor deviceColor, int quantity, double price) {
        this.user = user;
        this.deviceType = deviceType;
        this.deviceColor = deviceColor;
        this.quantity = quantity;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public double setPrice(DeviceType dtype) {
        if(dtype==DeviceType.ANDROID)
        {price = 900;}

        if(dtype==DeviceType.IPHONE)
        {
            price = 950;
        }
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DeviceColor getDeviceColor() {
        return deviceColor;
    }

    public void setDeviceColor(DeviceColor deviceColor) {
        this.deviceColor = deviceColor;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int id) {
        this.orderid = id;
    }

    public enum DeviceType{
        ANDROID, IPHONE
    }

    public enum DeviceColor{
        BLACK, WHITE, RED
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user; //inlcude user class

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @Enumerated(EnumType.STRING)
    private DeviceColor deviceColor;

    @Min(0)
    private int quantity;

    private double price;
}
