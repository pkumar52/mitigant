package com.itemservice.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itemservice.java.model.Item;
import com.itemservice.java.services.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	ItemService itemsvc;
	
	@PostMapping("/additem")
	public Item addItem(@RequestBody Item item)
	{	
		//add a new Item
		return itemsvc.addItem(item);
	}	
	
	@PutMapping("/changeitemdescription/{itemID}/{itemDescrption}")
	public String changeItemDescription(@PathVariable(value="itemID") int itemID, @PathVariable(value="itemDescrption") String itemDescrption)
	{
		//change an item description, by passing itemID and the new Description
		return itemsvc.changeItemDescription(itemID, itemDescrption);
	}
	
	@PutMapping("/updatestatus/{itemID}/{itemStatus}")
	public String updateItemStatus(@PathVariable(value="itemID") int itemID, @PathVariable(value="itemStatus") String itemStatus)
	{
		//mark item status as "done", or "not done"
		return itemsvc.updateItemStatus(itemID, itemStatus);
	}	
	
	@GetMapping("/getallitems/{notDone}")
	public List<Item> getAllItems(@PathVariable(value="notDone") int notDone)
	{
		//get all items with status as "not done", by passing parameter notDone = 1
		//get all items, with parameter notDone = 0
		return itemsvc.getAllItems(notDone);
	}
	
	/*@GetMapping("/getitem/{itemID}")
	public Item getItemById(@PathVariable(value="itemID") int itemID)
	{
		//get a single item by passing itemID as parameter
		return itemsvc.getItemById(itemID);
	}*/
	
	@GetMapping("/getitem/{itemID}")
	public ResponseEntity<Item> getItemById(@PathVariable(value="itemID") int itemID)
	{
		try
		{
			//get a single item by passing itemID as parameter
			Item item = itemsvc.getItemById(itemID);
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

