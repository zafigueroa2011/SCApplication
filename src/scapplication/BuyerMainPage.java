package scapplication;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *Creates a a frame for the buyer page.
 * @author Michael
 */
public class BuyerMainPage extends JFrame {

    /**
     *Main method for testing purposes.
     * @param args parameters from the console
     */
    public static void main(String[] args) {
	Buyer b = new Buyer("test","123");
	BuyerMainPage frameTabel = new BuyerMainPage(b);
}
	
	//different panels
	JPanel shoppingCart = new JPanel();
	JPanel buttons = new JPanel();
	JPanel buyerItems = new JPanel();
	JPanel scbuttons = new JPanel();
	//data for tables and headers for each
	Vector <String> header = new Vector<>();
    Vector <Vector> data = new Vector<>();
	
	Vector <String> header2 = new Vector<>();
    Vector <Vector> data2 = new Vector<>();
	//shopping cart table
	JTable scTable = new JTable(header2, data2);
	
	
	//buyer items table
	JTable bItems = new JTable(header, data);
	JScrollPane scrollPane2 = new JScrollPane(bItems);
	
	//Buttons
	JButton sellerAccount = new JButton("Seller Account");
	JButton checkout = new JButton("Checkout");
	JButton logout = new JButton("Logout");
	JButton addToCart = new JButton("Add To Cart");
	JButton removefromCart = new JButton("Remove From Cart");
	
	//GridLayout buyerMainPage = new GridLayout(0,4);
	//labels
	JLabel scLabel = new JLabel("Shopping Cart:");
	JLabel bItemsLabel = new JLabel("Items for Sale:");
	
	//final panel
	JPanel panel = new JPanel();

	BuyerMainPage(User b){
	super("Buyer Main Page");
	Buyer buyer = new Buyer(b.getUsername(),b.getPassword());
	setSize(1000,650);
	setLocation(500,280);
	panel.setLayout (null); 
	

	scLabel.setBounds(25,45,150,20);
	bItemsLabel.setBounds(500,45,150,20);
	shoppingCart.setBounds(0,70,500,1000);
	buttons.setBounds(400,0,600,50);
	buyerItems.setBounds(425,70,600,900);
	scbuttons.setBounds(0,0,400,50);
	
	//add buttons
	buttons.add(sellerAccount);
	buttons.add(checkout);
	buttons.add(logout);
	
	scbuttons.add(addToCart);
	scbuttons.add(removefromCart);
	
	
	//add tables
	shoppingCart.add(scTable);
	buyerItems.add(bItems);
	

	//Adding in container for final tables
	getContentPane().add(panel);
	uploadStorage(buyer);
	tableInit(buyer);
	bItems.setFillsViewportHeight(true);
    Container container = new Container();
    container.setLayout(new BorderLayout());
    container.add(bItems.getTableHeader(), BorderLayout.PAGE_START);
    container.add(bItems, BorderLayout.CENTER);
    JScrollPane scrollPane2 = new JScrollPane(bItems);
    container.add(scrollPane2);
    scrollPane2.setVisible(true);
    buyerItems.add(container);
    
    scTable.setFillsViewportHeight(true);
    Container container2 = new Container();
    container2.setLayout(new BorderLayout());
    container2.add(scTable.getTableHeader(), BorderLayout.PAGE_START);
    container2.add(scTable, BorderLayout.CENTER);
    JScrollPane scrollPane = new JScrollPane(scTable);
    container2.add(scrollPane);
    scrollPane.setVisible(true);
    shoppingCart.add(container2);
    
	//add panels
	panel.add(bItemsLabel);
	panel.add(scLabel);
	panel.add(buttons);
	panel.add(shoppingCart);
	panel.add(buyerItems);
	panel.add(scbuttons);
	
	actionBuyer(buyer);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
}

    /**
     *Initialize the header of the tables.
     */
    public void headerInit()
    {
        header.addElement("Name");
        header.addElement("Category");
        header.addElement("Quantity");
        header.addElement("Price");
        header.addElement("See Item Details");
        
    }

    /**
     *Initialize the header of the cart table.
     */
    public void headerInit2()
	{
        header2.addElement("Name");
        header2.addElement("Category");
        header2.addElement("Quantity");
        header2.addElement("Price");
	}

    /**
     *Initialize the tables of cart and storage.
     * @param b buyer
     */
    public void tableInit(Buyer b)
    {
        headerInit();
        headerInit2();
        dataInit(b);
        bItems = new JTable(new DefaultTableModel(data,header){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        
        scTable = new JTable(new DefaultTableModel(data2,header2){
        	public boolean isCellEditable(int row, int column)
        	{
        		
        		return column == 2;
        	}
        });
    }

    /**
     *Initialize the data for the storage table.
     * @param b buyer
     */
    public void dataInit(Buyer b)
    {
        for(int i = 0; i < b.storage.size(); i++)
        {
            addRow(b.storage.getItem(i));
        }
    }

    /**
     *Add a row to the storage table.
     * @param i item
     */
    public void addRow(Item i)
    {
        Vector <Object> row = new Vector <>();
        row.addElement(i.getName());
        row.addElement(i.getCategory());
        row.addElement(i.getQuantity());
        row.addElement(i.getSP());
        row.addElement(i.getDetails());
        data.add(row);
    }

    /**
     *Adds a row to the storage table.
     * @param scTable cart table
     * @param b buyer
     */
    public void checkout(JTable scTable, Buyer b){
		
		for(int i = 0; i < b.storage.size(); i++)
        {
            addRow(b.storage.getItem(i));
        }
	}

    /**
     *adds a row to the shopping cart table.
     * @param i item
     */
    public void addRowtoSC(Item i)
    {
        Vector <Object> row = new Vector <>();
        row.addElement(i.getName());
        row.addElement(i.getCategory());
        row.addElement(i.getQuantity());
        row.addElement(i.getSP());
        row.addElement(i.getDetails());
        data2.add(row);
    }

    /**
     *Get the storage to display items for sell.
     * @param buyer
     */
    public void refreshStorage(Buyer buyer)
	{
		uploadStorage(buyer);
		tableInit(buyer);
	}

    /**
     *Get all the items for sell.
     * @param b buyer
     */
    public void uploadStorage(Buyer b)
    {
        String itemInfo;
        try{     
            String filename = "Storage.txt";
            FileReader fr = new FileReader(filename); 
            BufferedReader br = new BufferedReader(fr);
            //get each item in the list and added to the seller's inventory
            while ((itemInfo = br.readLine())!= null)
            {    
                if(!"".equals(itemInfo))
                {
                    Item i = toItem(itemInfo);
                    b.storage.addItem(i);
                }
            }
            br.close();
            fr.close(); 
        }catch(IOException ie){System.err.print(ie);}   
    }

    /**
     *Converts a string to an item
     * @param s string with the information to create item
     * @return item
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

    /**
     *Remove a row from the cart table.
     * @param scTable the cart table
     */
    public void removeSelectedRows(JTable scTable){
		   DefaultTableModel model = (DefaultTableModel) this.scTable.getModel();
		   int[] rows = scTable.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     model.removeRow(rows[i]-i);
		   }
		}

    /**
     *Add a row to the cart table.
     * @param b buyer
     */
    public void addSelectedRows(Buyer b){
		   DefaultTableModel model = (DefaultTableModel) this.scTable.getModel();
		   int row = bItems.getSelectedRow();
                   Item i = b.storage.getItem(row);
                   b.sc.addToCart(i);
                   addRowtoSC(i);
                   model.fireTableDataChanged();
		}

    /**
     *Update the item quantity
     * @param i item
     */
    public void updateItemQnt(Item i)
    {
        String invFileName = i.getSeller() + "_Inventory.txt";
        try{
            BufferedReader file = new BufferedReader (new FileReader(invFileName));
            String line;
            String id = Integer.toString(i.getID());
            String input = "";
            while(null != (line = file.readLine()))
            {
                if(line.startsWith(id))
                {
                    Item old = toItem(line);
                    int qnt = old.getQuantity() - i.getQuantity();
                    old.setQuantity(qnt);
                    line = old.toString();
                }
                input += line + "\r\n";
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(invFileName);
            fileOut.write(input.getBytes());
            fileOut.close();
            updateItemStorage(i);
        }catch (IOException ex){System.out.println("Error in opening file");}
    }

    /**
     *Updates the item in the storage file.
     * @param i item
     */
    public void updateItemStorage(Item i)
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
                    Item old = toItem(line);
                    int qnt = old.getQuantity() - i.getQuantity();
                    old.setQuantity(qnt);
                    line = old.toString();
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
     *Updates the finances file.
     * @param i item
     */
    public void updateRevFile(Item i)
    {
        String filename = i.getSeller() + "_Finances.txt";
        try{
            BufferedReader file = new BufferedReader (new FileReader(filename));
            String line;
            String input = "";
            double totalRev;
            double newRev = i.getQuantity()*i.getSP();
            //if there is data already
            if(null != (line = file.readLine()))
            {
                if(line.startsWith(i.getSeller()))
                {
                    String[] data = line.split("-");
                    double oldRev = Double.parseDouble(data[1]);
                    totalRev = oldRev+newRev;
                    input = i.getSeller() + "-" + (Double.toString(totalRev))+ "-" + data[2]+"\r\n";;
                }
            }
            else
            {
                input = i.getSeller()+ "-" + (Double.toString(newRev)) + "\r\n";
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.write(input.getBytes());
            fileOut.close();
        }catch (IOException ex){System.out.println("Error in opening file");}
    }

    /**
     *Manages the listeners for the GUI elements in the frame or window.
     * @param b buyer
     */
    public void actionBuyer(final Buyer b)
    {
        //add listener to the table
        //add listener to the logout button
		addToCart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                addSelectedRows(b);
            }
        });
		removefromCart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                removeSelectedRows(scTable);
            }
        });
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                LoginPage login = new LoginPage();
                login.setVisible(true);
                dispose();
            }
        });
        //add listener to the logout button
        checkout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                //inside a loop for all the items in the SC
                for(int i = 0; i < b.sc.calculateSize(); i++)
                {
                    Item item = b.sc.getItem(i);
                    //call method to update the item quantity in the seller inventory
                    updateItemQnt(item);
                    //call method add revenues to the seller storage
                    updateRevFile(item);
                }
                LoginPage login = new LoginPage();
                login.setVisible(true);
                dispose();
            }
        });
        //add listener to the buyer account button
        sellerAccount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                User user = new User(b.getUsername(),b.getPassword());
                SellerMainPage seller = new SellerMainPage(user);
                seller.setVisible(true);
                dispose();
            }
        });
        scTable.getModel().addTableModelListener(new TableModelListener(){
            public void tableChanged(TableModelEvent tme) 
            {
            	int row = tme.getFirstRow();
                int column = tme.getColumn();
                TableModel model = (TableModel)scTable.getModel();
                String columnName = model.getColumnName(column);
                int oldData;
                boolean isChanged = false;
                if(column == 2 && isChanged == false)
                {
                    Object data = model.getValueAt(row, column);
                    int qnt = Integer.parseInt(data.toString());
                    Item i = b.sc.getItem(row);
                    oldData = i.getQuantity();

                    if(qnt < oldData && qnt > 0)
                    {
                        i.setQuantity(qnt);
                        scTable.setValueAt(Integer.toString(qnt), row, column);
                        scTable.editingCanceled(null);
                    }
                    else
                    {
                        scTable.editingCanceled(null);
                        //scTable.setValueAt(Integer.toString(oldData), row, column);
                    }
                    scTable.clearSelection();  
                }
            }
        });
    }
}
                
