/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author abdularifin
 */
public class ModelTransaction {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ModelCategory categoryId) {
        this.categoryId = categoryId;
    }

    public ModelCategory getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(ModelCategory categoryName) {
        this.categoryName = categoryName;
    }

    public ModelUser getUserId() {
        return userId;
    }

    public void setUserId(ModelUser userId) {
        this.userId = userId;
    }

    public ModelUser getUserName() {
        return userName;
    }

    public void setUserName(ModelUser userName) {
        this.userName = userName;
    }

    public ModelCustomer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(ModelCustomer customerId) {
        this.customerId = customerId;
    }

    public ModelCustomer getCustomerName() {
        return customerName;
    }

    public void setCustomerName(ModelCustomer customerName) {
        this.customerName = customerName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
    private int id;
    private ModelCategory categoryId;
    private ModelCategory categoryName;
    private ModelUser userId;
    private ModelUser userName;
    private ModelCustomer customerId;
    private ModelCustomer customerName;
    private int weight;
    private int price;

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    private Date created_at;
    private Date updated_at;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    private boolean status;
}
