/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Creates a login frame.
 * @author Michael
 */
public class LoginPage extends JFrame{
	
    /**
     *Main function for testing purposes
     * @param args
     */
    public static void main(String[] args) {
            LoginPage frameTable = new LoginPage();
	}
		
	//buttons
	JButton bLogin = new JButton("Login");
	JButton createAccount = new JButton("Create Account");
	
	JPanel panel = new JPanel();
	
	//textfields
	JTextField txtUser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	//labels for text fields
	JLabel lblname = new JLabel("Username:");
	JLabel lblpassword = new JLabel("Password:");
	//error label
	JLabel lblmess = new JLabel("");
	
	//drop down menu
	private String[] accountType = {"Buyer","Seller"};
	//dropdown object
	JComboBox dropdown = new JComboBox();
	
	private int count = 0;
	
    /**
     *
     */
    public void init() 
        {
	    for (int i = 0; i < 2; i++)
            {
                dropdown.addItem(accountType[count++]);
            }
	}
	    
	LoginPage()
        {
            super("Login Authentication");
            setSize(400,300);
            setLocation(500,280);
            panel.setLayout(null);
		
		
            txtUser.setBounds(70,30,250,20);
            pass.setBounds(70,75,250,20);
            bLogin.setBounds(70,110,80,20);
            createAccount.setBounds(180,110,140,20);

            lblname.setBounds(140, 10, 120, 10);
            lblpassword.setBounds(140, 60, 120, 10);
            lblmess.setBounds(70, 200, 200, 30);

            dropdown.setBounds(140, 175, 120, 20);

            panel.add(bLogin);
            panel.add(pass);
            panel.add(txtUser);
            panel.add(createAccount);

            panel.add(lblname);
            panel.add(lblpassword);
            panel.add(lblmess);

            panel.add(dropdown);


            getContentPane().add(panel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            actionLogin();
            init();
			
	}

    /**
     *
     */
    public void actionLogin()
        {	
            bLogin.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
                    String username = txtUser.getText();
                    String password = pass.getText();
						
                    if(checkAccountInfo("accounts.txt",username,password) == true){
                        User user = new User(username,password);
                        openPage(user);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Wrong Password/Username");
                        txtUser.setText("");
                        pass.setText("");
                        txtUser.requestFocus();
                    }
		}
            });
            createAccount.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae2){
                    if(ae2.getSource() == createAccount)
                    {
                        String username = txtUser.getText();
                        String password = new String(pass.getPassword());
                        if(!checkBlank(username,password, null, null))
                        {
                            if(!checkExist("accounts.txt",username))
                            {    
                                User user = new User(username,password);
                                saveToFile("accounts.txt",user);
                                openPage(user);
                                dispose();
                            }
                        }  
                    }		
		}
            });	
	}

    /**
     *
     * @param filename
     * @param u
     */
    public void saveToFile(String filename,User u){
        try{         
            FileWriter fw = new FileWriter(filename,true); 
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(u.toString());
            bw.newLine();
            bw.flush();
            bw.close();
            showMess("The account is created.",lblmess);
        }catch(IOException ie){System.err.print(ie);}      
	}
        
    /**
     *
     * @param filename
     * @param name
     * @return
     */
    public boolean checkExist(String filename, String name)
        {    
            FileReader fr;
            BufferedReader br;
            String accinfo;
            boolean exist=false;
            try
            {  
                fr=new FileReader(filename);
                br=new BufferedReader(fr);
                while ((accinfo = br.readLine())!=null)
                {
                    if(check(accinfo,name))
                    {
                        showMess("The account already exists.",lblmess);
                        exist=true;
                        break;
                    }
                }
                br.close();
                fr.close(); 
                
            }catch(IOException ie){System.err.print(ie);}
            return exist;                          
        }

    /**
     *
     * @param filename
     * @param username
     * @param password
     * @return
     */
    public boolean checkAccountInfo(String filename, String username, String password ){
            boolean login = false;
            FileReader fr;
            BufferedReader br;
            String accinfo;
            try
            {    
                
                fr=new FileReader(filename);
                br=new BufferedReader(fr);
                
                while ((accinfo=br.readLine())!=null){

                   if(check(accinfo,username,password))
                   {
                        login=true;
                        break;
                   }
                }

                if(!login) showMess("Invalid login",lblmess);
                
                br.close();
                fr.close();    
            } catch(IOException ie){System.err.print(ie);}
            return login;
        }
        
    /**
     *
     * @param username
     * @param password
     * @param namemess
     * @param passwmess
     * @return
     */
    public boolean checkBlank(String username, String password, JLabel namemess, JLabel passwmess){
            boolean hasBlank=false;
            if(username.length()<1){
                showMess("User name is required.",namemess);
                hasBlank=true;
            }
            if(password.length()<1){
                showMess("Password is required.",passwmess);
                hasBlank=true;
            }
            return hasBlank;                                                                  
	}

    /**
     *
     * @param mess
     * @param lbl
     */
    public void showMess(String mess, JLabel lbl){
            lbl.setText(mess);
            lbl.setForeground(Color.RED);        
	}

    /**
     *
     * @param accinfo
     * @param username
     * @param password
     * @return
     */
    public boolean check(String accinfo, String username, String password){
            String[] info = accinfo.split("-");
            String fileUsername = info[0];
            String filePass = info[1] ;
            if(username.equals(fileUsername) && password.equals(filePass))
                return true;
            else return false;
                   
        }

    /**
     *
     * @param accinfo
     * @param name
     * @return
     */
    public boolean check(String accinfo, String name){
            String[] info=accinfo.split("-");
            String uname = info[0];
            if(uname.equals(name))
                return true;
            else return false;
	}

    /**
     *
     */
    public void createStorage()
        {
            try{
                BufferedReader file = new BufferedReader (new FileReader("Storage.txt"));
                file.close();
            }catch (FileNotFoundException ex){
                try {
                    BufferedWriter file = new BufferedWriter(new FileWriter("Storage.txt",true));
                    file.close();
                } catch (IOException ie) {System.err.println(ex);}
            } 
            catch (IOException ex) {System.err.println(ex);
            }
        }

    /**
     *
     * @param user
     */
    public void openPage(User user)
        {
            //create the storage file if it has not been created
            createStorage();
            if(dropdown.getSelectedItem().equals("Buyer")){
                BuyerMainPage buyerpage = new BuyerMainPage(user);
                buyerpage.setVisible(true);
            }
            if(dropdown.getSelectedItem().equals("Seller")){
                SellerMainPage sellerpage = new SellerMainPage(user);	
                sellerpage.setVisible(true);
            }
        }                     
}
