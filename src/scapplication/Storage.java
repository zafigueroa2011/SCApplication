package scapplication;

import java.util.ArrayList;

/**
 *
 * @author Zaylin
 */
public class Storage {
	
	Storage(){
		
		storage = new ArrayList<>();
		
	}
	void addItem(Item i)
    {
        storage.add(i);
    }
	int searchItem(Item e)
    {
        for(int i = 0; i < storage.size(); i++)
        {
            if(e.getID() == storage.get(i).getID())
            {
                return i;
            }
        }
        return -1;
    }
	void removeItem(Item e)
    {
        int search = searchItem(e);
        
        if(search != -1)
        {
            storage.remove(search);
        }
    }
	Item getItem(int i)
    {
        return storage.get(i);
    }
	int size()
    {
        return storage.size();
    }
	
	
	
    ArrayList<Item> storage;
}
