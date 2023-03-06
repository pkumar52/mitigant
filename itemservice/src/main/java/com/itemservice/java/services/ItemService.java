package com.itemservice.java.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itemservice.java.dao.ItemRepository;
import com.itemservice.java.model.Item;

@Component
public class ItemService {
	
	@Autowired
	ItemRepository itemrep;	
	
	public Item addItem (Item item)
	{
		try 
		{
		    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		    
		    item.setCreationdate(dtf.format(LocalDateTime.now()));
		    
		    //set default done date 
		    item.setDonedate("0002-11-29 00:00:00");
		    
		    Date creationdate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(item.getCreationdate());
			Date duedate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(item.getDuedate());
			
			//if the creation date is greater than the due date, set the item status as "past due"
			if ((duedate.getTime() - creationdate.getTime()) <= 0)
			{
				//item.setDuedate(dtf.format(LocalDateTime.now()));
				item.setDonedate(dtf.format(LocalDateTime.now()));
				item.setStatus("past due");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		itemrep.save(item);
		return item;
	}	
	
	public String changeItemDescription(int itemID, String itemDescrption)
	{
		Item item= itemrep.findById(itemID).get();
		item.setDescription(itemDescrption);
		
		//if item status is "past due" no changes to the item
		if (item.getStatus().equals("past due"))
			return "Change Failed";
		
		itemrep.save (item);
		return "Change Success";
	}
	
	public String updateItemStatus(int itemID, String status)
	{
		Item item= itemrep.findById(itemID).get();
		//if item status is "past due" no changes to the item
		if (item.getStatus().equals("past due"))
			return "Update Failed";
		
		if (status.equals("done"))
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
			item.setDonedate(dtf.format(LocalDateTime.now()));
		}
		
		item.setStatus(status);
		itemrep.save (item);
		return "Update Success";
	}	
	
	public List<Item> getAllItems(int notDone)
	{
		if (notDone == 1)
		{
			List<Item> itemlistnew = new ArrayList<Item>();
			List<Item> itemlist = itemrep.findAll();
			Iterator<Item> itemitr = itemlist.iterator();
			while(itemitr.hasNext())
			{
				Item tmpItem = itemitr.next();
				if (tmpItem.getStatus().equals("not done"))
					itemlistnew.add(tmpItem);
			}
			return itemlistnew;
		}else
		
		return itemrep.findAll();
	}
	
	public Item getItemById(int itemID)
	{
		return itemrep.findById(itemID).get();
	}

}
