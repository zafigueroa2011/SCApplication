/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scapplication;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Zaylin
 */
public class SellerMainPage extends JFrame 
{
//    public static void main(String[] args) 
//    {
//        Seller s = new Seller("test","123");
//        SellerMainPage frameTabel = new SellerMainPage(s);
//    }

    //panels
    JPanel finPanel = new JPanel();     //panel for finances
    JPanel addItemPanel = new JPanel(); //panel for the add item section
    JPanel invPanel = new JPanel();     //panel for the inventory
    JPanel leftPanels = new JPanel();
    JPanel rightPanels = new JPanel();
    JPanel allPanels = new JPanel();
    
    //vectors for the information on the table
    Vector <String> header = new Vector<>();
    Vector <Vector> data = new Vector<>();
    
    //table for inventory
    JTable inventory;
    
    //buttons
    JButton addItem = new JButton("Add Item");
    JButton cancel = new JButton("Cancel");
    JButton buyerAcc = new JButton("Buyer Account");
    JButton logout = new JButton("Logout");
    JButton remove = new JButton("Remove Item");
    
    
    //textfields
    JTextField itemName = new JTextField(15);
    JTextField itemCategory = new JTextField(15);
    JTextField itemQuantity = new JTextField(10);
    JTextField itemIP = new JTextField(10);
    JTextField itemSP = new JTextField(10);
    
    //text area for details
    JTextArea itemDetails = new JTextArea(5,20);
    
    //labels
    JLabel namelbl = new JLabel("Name:");
    JLabel categorylbl = new JLabel("Category:");
    JLabel iplbl = new JLabel("Invoice Price:");
    JLabel splbl = new JLabel("Selling Price:");
    JLabel detailslbl = new JLabel("Details:");
    JLabel quantitylbl = new JLabel("Quantity:");
    JLabel costslbl = new JLabel("Costs:");
    JLabel revenueslbl = new JLabel("Revenues:");
    JLabel profitslbl = new JLabel("Profits:");
    JLabel invSizelbl = new JLabel("Inventory Size:");
    JLabel costsVal = new JLabel();
    JLabel revenuesVal = new JLabel();
    JLabel profitsVal = new JLabel();
    JLabel invSizeVal = new JLabel();
    
    /**
     * Constructs a sellerMainPage frame
     * @param s the user
     */
    SellerMainPage(User s)
    {
        super("Seller Page");
        Seller seller = new Seller(s.getUsername(),s.getPassword());
        setSize(1200,800);
        setLocation(100,100);
        Dimension dim = new Dimension(600,600);
        
        //draw the finances panel
        costsVal.setText("0.00");
        revenuesVal.setText("0.00");
        profitsVal.setText("0.00");
        invSizeVal.setText("0.00");
        finPanel.setName("Finances");
        finPanel.add(costslbl);
        finPanel.add(costsVal);
        finPanel.add(revenueslbl);
        finPanel.add(revenuesVal);
        finPanel.add(profitslbl);
        finPanel.add(profitsVal);
        finPanel.add(invSizelbl);
        finPanel.add(invSizeVal);
        
        finPanel.setLayout(new GridLayout(0,2,5,5));
        
        //draw the add item panel
        addItemPanel.add(namelbl);
        addItemPanel.add(itemName);
        addItemPanel.add(quantitylbl);
        addItemPanel.add(itemQuantity);
        addItemPanel.add(categorylbl);
        addItemPanel.add(itemCategory);
        addItemPanel.add(splbl);
        addItemPanel.add(itemSP);
        addItemPanel.add(iplbl);
        addItemPanel.add(itemIP);
        addItemPanel.add(detailslbl);
        addItemPanel.add(itemDetails);
        JPanel buttons = new JPanel();
        buttons.add(addItem);
        buttons.add(cancel);
        addItemPanel.add(buttons);
        //addItemPanel.add(cancel);
        
        addItemPanel.setLayout(new GridLayout(0,2,2,2));
        
        leftPanels.add(finPanel);
        leftPanels.add(addItemPanel);
        
        //draw the inventory table and buttons
        uploadInventory(seller);
        updateFinancesPanel(seller);
        tableInit(seller);
        inventory.setFillsViewportHeight(true);
        Container container = new Container();
        container.setLayout(new BorderLayout());
        container.add(inventory.getTableHeader(), BorderLayout.PAGE_START);
        container.add(inventory, BorderLayout.CENTER);
        JScrollPane pane = new JScrollPane(inventory);
        pane.setVisible(true);
        container.add(pane);

        rightPanels.add(buyerAcc);
        rightPanels.add(logout);
        rightPanels.add(remove);
        rightPanels.add(container);
        
        rightPanels.setLayout(new FlowLayout());
        leftPanels.setPreferredSize(dim);
        leftPanels.setLayout(new FlowLayout());
        //leftPanels.setLayout(new BoxLayout(leftPanels,BoxLayout.Y_AXIS));
        
        allPanels.add(leftPanels);
        allPanels.add(rightPanels);
        allPanels.setLayout(new BoxLayout(allPanels,BoxLayout.X_AXIS));
        
        getContentPane().add(allPanels);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        actionSeller(seller);
        setVisible(true);
    }
    /**
     * Initializes the data for the Inventory table.
     * @param s seller
     */
    public void dataInit(Seller s)
    {
        for(int i = 0; i < s.inv.size(); i++)
        {
            addRow(s.inv.getItem(i));
        }
    }
    /**
     * Initializes the header of the inventory of the table.
     */
    public void headerInit()
    {
        header.addElement("Name");
        header.addElement("ID");
        header.addElement("Quantity");
        header.addElement("Category");
        header.addElement("Selling Price");
        header.addElement("Invoice Price");
        header.addElement("Details");
    }
    /**
     * Initializes the Inventory table.
     * @param s seller
     */
    public void tableInit(Seller s)
    {
        headerInit();
        dataInit(s);
        inventory = new JTable(new DefaultTableModel(data,header){
            public boolean isCellEditable(int row, int column)
            {
                return column != 1;
            }
        });
    }
    /**
     * Manage the tables and buttons listeners.
     * @param s seller
     */
    public void actionSeller(Seller s)
    {
        //add listeners to the add item button
        addItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                addItemtoInv(s);
                clearTextFields();
                updateFinancesPanel(s);
            }
        });
        //add listener to the table
        inventory.getModel().addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent tme) 
            {
                int row = tme.getFirstRow();
                int column = tme.getColumn();
                TableModel model = (TableModel)tme.getSource();
                String columnName = model.getColumnName(column);
                if(column >= 0)
                {
                    Object data = model.getValueAt(row, column);
                    Item i = s.inv.getItem(row);
                    if(!data.toString().equals(""))
                    {
                        try{
                            if(null != columnName)
                            switch (columnName) {
                                case "Name":
                                    //replace the new value on the seller inventory
                                    String name = data.toString();
                                    i.setName(name);
                                    updateItem(s,i,false);
                                    break;
                                case "Quantity":
                                    //replace the new value on the seller inventory
                                    int q = Integer.parseInt(data.toString());
                                    //calculate the costs if the new quantity is greater than the previous one
                                    if(i.getQuantity() < q)
                                    {
                                        double costs = (q-i.getQuantity())*i.getIP();
                                        s.setCosts(costs);
                                    }
                                    i.setQuantity(q);
                                    updateItem(s,i,false);
                                    updateFinancesPanel(s);
                                    break;
                                case "Category":
                                    //replace the new value on the seller inventory
                                    String cat = data.toString();
                                    i.setCategory(cat);
                                    updateItem(s,i,false);
                                    break;
                                case "Selling Price":
                                    //replace the new value on the seller inventory
                                    double sp = Double.parseDouble(data.toString());
                                    i.setSP(sp);
                                    updateItem(s,i,false);
                                    break;
                                case "Invoice Price":
                                    //replace the new value on the seller inventory
                                    double ip = Double.parseDouble(data.toString());
                                    i.setIP(ip);
                                    updateItem(s,i,false);
                                    updateFinancesPanel(s);
                                    break;
                                case "Details":
                                    //replace the new value on the seller inventory
                                    String det = data.toString();
                                    i.setDetails(det);
                                    updateItem(s,i,false);
                                    break;
                                default:
                                    inventory.clearSelection();
                                    break;
                            }
                        inventory.clearSelection();
                        }catch(NumberFormatException ex){System.out.println("Error while parsing the information");}
                        //replace the new value on the file
                    }
                    else
                    {
                        Object oldData = "";
                        JOptionPane.showMessageDialog(null, "Invalid text entered!");
                        if(null != columnName)
                        switch (columnName) {
                            case "Name":
                                oldData = i.getName();
                                break;
                            case "Quantity":
                                oldData = i.getQuantity();
                                break;
                            case "Category":
                                oldData = i.getCategory();
                                break;
                            case "Selling Price":
                                oldData = i.getSP();
                                break;
                            case "Invoice Price":
                                oldData = i.getIP();
                                break;
                            default:
                                oldData = i.getDetails();
                                break;
                        }
                        inventory.setValueAt(oldData.toString(), row, column);
                        inventory.clearSelection();
                        inventory.editingCanceled(null);
               }
            }
            }
        });
        //add listener to the remove button
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                DefaultTableModel model = (DefaultTableModel) inventory.getModel();
		int row = inventory.getSelectedRow();
                if(row >= 0)
                {
                    //remove item from the inventory
                    Item item = s.inv.getItem(row);
                    s.inv.removeItem(item);
                    //remove item from the table
                    model.removeRow(row);
                    //remove item from the file
                    updateItem(s,item,true);
                    inventory.clearSelection();
                }
                updateFinancesPanel(s);
            }
        });
        //add listener to the logout button
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                updateFinFile(s);
                LoginPage login = new LoginPage();
                login.setVisible(true);
                dispose();
            }
        });
        //add listener to the cancel button
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                clearTextFields();
                inventory.clearSelection();
            }
        });
        //add listener to the buyer account button
        buyerAcc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                updateFinFile(s);
                User user = new User(s.getUsername(),s.getPassword());
                BuyerMainPage buyer = new BuyerMainPage(user);
                buyer.setVisible(true);
                dispose();
            }
        });
    }
    /**
     * Clears the textfields in the add item panel.
     */
    public void clearTextFields()
    {
        itemDetails.setText("");
        itemName.setText("");
        itemCategory.setText("");
        itemQuantity.setText("");
        itemSP.setText("");
        itemIP.setText("");
    }
    /**
     * Adds and item to the seller inventory and the inventory table.Also recalculates the finances.
     * @param s seller
     */
    public void addItemtoInv(Seller s)
    {
        //make the item object
        String d = itemDetails.getText();
        String n = itemName.getText();
        String c = itemCategory.getText();
        String seller = s.getUsername();
        int q = 0;
        double sp = 0;
        double ip = 0;
        try{
            q = Integer.parseInt(itemQuantity.getText());
            sp = Double.parseDouble(itemSP.getText());
            ip = Double.parseDouble(itemIP.getText());
        }catch(Exception ex){System.out.println("textfield is empty or it is a string");}
        Item i = new Item(n,c,d,q,ip,sp,seller);  
        //add item to the seller's inventory
        s.inv.addItem(i);
        //add to the inventory file
        saveItemToFile(s,i);
        //recalculate the costs
        double costs = i.getIP()*i.getQuantity();
        s.setCosts(costs);
        //add the new item to the data of the table
        addRow(i);
        //update the table with the new item
        DefaultTableModel dm = (DefaultTableModel)inventory.getModel();
        dm.fireTableDataChanged(); 
    }
    /**
     * Updates an item in the seller inventory file.
     * @param s seller
     * @param i item
     * @param isRemoved variable that notifies if the item was removed
     */
    public void updateItem(Seller s, Item i, boolean isRemoved)
    {
        try{
            String filename = s.getUsername() + "_Inventory.txt";
            BufferedReader file = new BufferedReader (new FileReader(filename));
            String line;
            String id = Integer.toString(i.getID());
            String input = "";
            while(null != (line = file.readLine()))
            {
                if(line.startsWith(id))
                {
                    if(!isRemoved)
                    {
                        line = i.toString();
                    }
                    else
                    {
                        line = "";
                    }
                }
                input += line + "\r\n";
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.write(input.getBytes());
            fileOut.close();
            updateItemStorage(s,i,isRemoved);
        }catch (IOException ex){System.out.println("Error in opening file");}
        catch(Exception ex){System.out.println(ex);}
    }
    /**
     * Updates an Item in the storage file.
     * @param s seller
     * @param i item
     * @param isRemoved variable that notifies if the item was removed
     */
    public void updateItemStorage(Seller s, Item i, boolean isRemoved)
    {
        try{
            BufferedReader storage = new BufferedReader (new FileReader("Storage.txt"));
            String line;
            String id = Integer.toString(i.getID());
            String input = "";
            while(null != (line = storage.readLine()))
            {
                if(line.startsWith(id))
                {
                    if(!isRemoved)
                    {
                        line = i.toString();
                    }
                    else
                    {
                        line = "";
                    }
                }
                input += line + "\r\n";
            }
            storage.close();
            FileOutputStream storageOut = new FileOutputStream("Storage.txt");
            storageOut.write(input.getBytes());
            storageOut.close();
        }catch (IOException ex){System.out.println("Error in opening file");}
        catch(Exception ex){System.out.println(ex);}
    }
    /**
     * Adds a row to the inventory table.
     * @param i item to add to the table
     */
    public void addRow(Item i)
    {
        Vector <Object> row = new Vector <>();
        row.addElement(i.getName());
        row.addElement(i.getID());
        row.addElement(i.getQuantity());
        row.addElement(i.getCategory());
        row.addElement(i.getSP());
        row.addElement(i.getIP());
        row.addElement(i.getDetails());
        data.add(row);
    }
    /**
     * Save item to the seller inventory and storage file.
     * @param s seller
     * @param i item
     */
    public void saveItemToFile(Seller s, Item i)
    {
        try{     
            String filename = s.getUsername() + "_Inventory.txt";
            BufferedWriter invFile = new BufferedWriter(new FileWriter(filename,true));
            BufferedWriter storageFile = new BufferedWriter(new FileWriter("Storage.txt",true));
            invFile.write(i.toString());
            storageFile.write(i.toString());
            invFile.newLine();
            storageFile.newLine();
            storageFile.flush();
            invFile.flush();
            storageFile.close();
            invFile.close();
        }catch(IOException ie){System.err.print(ie);}      
    }
    /**
     * Uploads the inventory stored of the seller
     * @param s the seller
     */
    public void uploadInventory(Seller s)
    {
        String itemInfo;
        try{     
            String filename = s.getUsername() + "_Inventory.txt";
            FileReader fr = new FileReader(filename); 
            BufferedReader br = new BufferedReader(fr);
            //get each item in the list and added to the seller's inventory
            while ((itemInfo = br.readLine())!= null)
            {    
                if(!"".equals(itemInfo))
                {
                    Item i = toItem(itemInfo);
                    s.inv.addItem(i);
                }
            }
            br.close();
            fr.close();
            //update the finances
            uploadFinances(s);
        }catch (FileNotFoundException ex){
            //create the inventory and finance file if not found
            String invFile = s.getUsername() + "_Inventory.txt";
            String finFile = s.getUsername() + "_Finances.txt";
            try {
                BufferedWriter bwInv = new BufferedWriter(new FileWriter(invFile,true));
                BufferedWriter bwFin = new BufferedWriter(new FileWriter(finFile,true));
                bwInv.close();
                bwFin.close();
            }catch(IOException io){System.err.print(io); }
            
        }
        catch(IOException ie){System.err.print(ie);}   
    }
    /**
     * Save the finances of the seller using the seller finances file.
     * @param s seller
     */
    public void uploadFinances(Seller s)
    {
        String finInfo;
        try{     
            String filename = s.getUsername() + "_Finances.txt";
            FileReader fr = new FileReader(filename); 
            BufferedReader br = new BufferedReader(fr);
            //get the stored finances and save it to the seller
            while ((finInfo = br.readLine())!= null)
            {    
                if(!"".equals(finInfo))
                {
                    try{
                        String[] data = finInfo.split("-");
                        double r = Double.parseDouble(data[1]);
                        double c = Double.parseDouble(data[2]);
                        s.setRevenues(r);
                        s.setCosts(c);
                    }catch(Exception e){System.err.print(e);}
                }
            }
            br.close();
            fr.close();
            s.calculateProfits();
        }catch(IOException ie){System.err.print(ie);}   
    }
    /**
     * Updates the Finances Panel with the latest values.
     * @param s seller
     */
    public void updateFinancesPanel(Seller s)
    {
        //calculate finances
        double c = s.getCosts();
        double p = s.calculateProfits();
        double r = s.getRevenues();
        int size = s.inv.size();
        //update the finances displayed
        costsVal.setText(Double.toString(c));
        profitsVal.setText(Double.toString(p));
        revenuesVal.setText(Double.toString(r));
        invSizeVal.setText(Integer.toString(size));
    }
    /**
     * Updates the finances file of the seller.
     * @param s seller
     */
    public void updateFinFile(Seller s)
    {
        String filename = s.getUsername()+ "_Finances.txt";
        try{
            BufferedReader file = new BufferedReader (new FileReader(filename));
            String line = file.readLine();
            String input = "";
            double totalCosts;
            String[] data = line.split("-");
            double costs = Double.parseDouble(data[2]);
            input = s.getUsername()+"-"+ data[1]+ "-"+ s.getCosts()+ "\r\n";;
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.write(input.getBytes());
            fileOut.close();
        }catch (IOException ex){System.out.println("Error in opening file");}
    }
    /**
     * Converts a string to an item object.
     * @param s string with the information to creating the item
     * @return an item object with the information of the string
     */
    public Item toItem(String s)
    {
        String[] item =s.split("-");
        String n = item[1];
        String c = item[5];
        String d = item[6];
        String seller = item[7];
        int itemID = 0;
        String qnt = item[2];
        int q = 0;
        double invP = 0;
        double sellP = 0;
        try{
            itemID = Integer.parseInt(item[0]);
            q = Integer.parseInt(qnt);
            invP = Double.parseDouble(item[4]);
            sellP = Double.parseDouble(item[3]);
            
        }catch(Exception ex){System.out.println("error converting the string to int or doubles");}
        
        Item i = new Item(n,c,d,itemID,q,invP,sellP,seller);       
        return i;
    }
}

