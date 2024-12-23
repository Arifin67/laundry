/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import model.ModelCustomer;

/**
 *
 * @author abdularifin
 */
public interface CustomerInterface {
    void addUser (ModelCustomer cs);
    void updateUser (ModelCustomer cs);
    void deleteUser (ModelCustomer cs);
    
    List<ModelCustomer> showUser();
    List<ModelCustomer> findUserByName(String name);
}
