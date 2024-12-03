/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import model.ModelUser;

/**
 *
 * @author abdularifin
 */
public interface UserInterface {
    void addUser (ModelUser model);
    void updateUser (ModelUser model);
    void deleteUser (ModelUser model);
    
    List<ModelUser> showUser();
    
}
