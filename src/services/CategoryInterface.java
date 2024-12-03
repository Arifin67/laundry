/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import java.util.Map;
import model.ModelCategory;
import model.ModelService;

/**
 *
 * @author abdularifin
 */
public interface CategoryInterface {
    void addCategory(ModelService service, List<ModelCategory> categories);
    void editCategory( ModelService service, List<ModelCategory> categories);
    void deleteCategory( ModelCategory category);
    
    List<ModelCategory> showCategory();
}
