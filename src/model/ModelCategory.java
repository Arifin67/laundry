/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author abdularifin
 */
public class ModelCategory {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModelService getServiceId() {
        return serviceId;
    }

    public void setServiceId(ModelService serviceId) {
        this.serviceId = serviceId;
    }

    public ModelService getServiceName() {
        return serviceName;
    }

    public void setServiceName(ModelService serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    
private int id;
private ModelService serviceId;
private ModelService serviceName;
private String name;
private int price;
    
}
