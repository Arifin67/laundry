/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.ModelCategory;
import model.ModelCustomer;
import model.ModelService;
import model.ModelTransaction;
import model.ModelUser;
import services.TransactionInterface;

/**
 *
 * @author abdularifin
 */
public class ControllerTransaction implements TransactionInterface {

    @Override
    public void addTransaction(ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction) {
        
    }

    @Override
    public void editTransaction(ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteTransaction(ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelCategory> showTransaction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
