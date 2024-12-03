package controller;

import view.loginForm;
import view.menuUtama;
import model.ModelLogin;
import model.ModelLoginResult;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControllerLogin {
    private loginForm view;
    private ModelLogin model;

    public ControllerLogin(loginForm view) {
        this.view = view;
        this.model = new ModelLogin(); // Inisialisasi model
        this.view.getBtnLogin().addActionListener(new LoginButtonListener());
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsernameField().getText();
            String password = new String(view.getPasswordField().getPassword());
            
            if (username.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "please insert username");
                return;
            }else if (password.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "please insert password");
                return;
            }

            ModelLoginResult result = model.processLogin(username, password);// Memanggil login dari model

            if (result.isAuthenticated()) {
                new menuUtama(result.getUsername(), result.getRole()).setVisible(true);
                view.dispose(); // Menutup form login
                return;
            } else {
                JOptionPane.showMessageDialog(null, "username or password is wrong, please try again!");
                return;
            }
        }
    }
}