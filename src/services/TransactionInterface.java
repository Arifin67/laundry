/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import model.ModelCategory;
import model.ModelCustomer;
import model.ModelService;
import model.ModelTransaction;
import model.ModelUser;

/**
 *
 * @author abdularifin
 */
public interface TransactionInterface {
    void addTransaction(ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction);
    void editTransaction( ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction);
    void deleteTransaction( ModelService service, List<ModelCategory> categories, ModelUser user, ModelCustomer customer, ModelTransaction transaction);
    
    List<ModelCategory> showTransaction();
}
