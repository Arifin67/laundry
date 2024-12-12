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
    int addTransaction(ModelService service, ModelCategory category, ModelUser user, ModelCustomer customer, ModelTransaction transaction);
    void editTransaction(ModelTransaction transaction);
    void deleteTransaction( ModelService service, ModelCategory category, ModelUser user, ModelCustomer customer, ModelTransaction transaction);
    
    List<ModelTransaction> showTransaction();
    List<ModelTransaction> showTransactionByNow();
    double showTransactionForProfit();
}
