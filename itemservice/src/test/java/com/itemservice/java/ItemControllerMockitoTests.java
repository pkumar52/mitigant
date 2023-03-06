package com.itemservice.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itemservice.java.controller.ItemController;
import com.itemservice.java.model.Item;
import com.itemservice.java.services.ItemService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ItemControllerMockitoTests.class})
class ItemControllerMockitoTests {
	
	@Mock
	ItemService itemService;
	
	@InjectMocks
	ItemController itemController;
	
	List<Item> myItems1, myItems2;
	Item item1, item2;
	
	@Test
	@Order(1)
	void test_addItem() 
	{
		item1 = new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00");
		int itemID = 1;
		
		when(itemService.addItem(item1)).thenReturn(item1);
		
		Item res = itemController.addItem(item1);
		
		assertEquals(itemID, res.getId());
	}	
	
	@Test
	@Order(2)
	void test_changeItemDescription() 
	{
		item1 = new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00");
		int itemID = 1;
		String newDescription = "item two", success = "Change Success", failed = "Change Failed";
		
		when(itemService.changeItemDescription(itemID, newDescription)).thenReturn(success);
		
		String res1 = itemController.changeItemDescription(itemID, newDescription);
		
		assertEquals(success, res1);
		
		item2 = new Item(1, "item one", "past due", "0000-00-00", "2023-03-03", "0000-00-00");
		
		when(itemService.changeItemDescription(itemID, newDescription)).thenReturn(failed);
		
		String res2 = itemController.changeItemDescription(itemID, newDescription);
		
		assertEquals(failed, res2);		
	}	
	
	@Test
	@Order(3)
	void test_updateItemStatus() //mark item status as "done", or "not done"
	{
		item1 = new Item(1, "item one", "done", "0000-00-00", "2023-03-03", "0000-00-00");
		int itemID1 = 1;
		String status1 = "done", success = "Update Success", failed = "Update Failed";;
		
		when(itemService.updateItemStatus(itemID1, status1)).thenReturn(success);
		
		String res1 = itemController.updateItemStatus(itemID1, status1);
		
		assertEquals(success, res1);
		
		item2 = new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00");
		int itemID2 = 1;
		String status2 = "not done";
		
		when(itemService.updateItemStatus(itemID2, status2)).thenReturn(failed);
		
		String res2 = itemController.updateItemStatus(itemID2, status2);
		
		assertEquals(failed, res2);		
	}	

	@Test
	@Order(4)
	void test_getAllItems() 
	{
		myItems1 = new ArrayList<Item>();
		
		myItems1.add(new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00"));
		myItems1.add(new Item(2, "item two", "done", "0000-00-00", "2023-03-04", "0000-00-00"));
		myItems1.add(new Item(3, "item three", "not done", "0000-00-00", "2023-03-05", "0000-00-00"));
		
		when(itemService.getAllItems(0)).thenReturn(myItems1);
		
		//list of all items
		List<Item> res1 = itemController.getAllItems(0);
		assertEquals(3, res1.size());
		
		myItems2 = new ArrayList<Item>();
		
		myItems2.add(new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00"));
		myItems2.add(new Item(3, "item three", "not done", "0000-00-00", "2023-03-05", "0000-00-00"));
		
		when(itemService.getAllItems(1)).thenReturn(myItems2);
		
		//list of "not done" items
		List<Item> res2 = itemController.getAllItems(1);
		assertEquals(2, res2.size());		
	}
	
	@Test
	@Order(5)
	void test_getItemById() 
	{
		item1 = new Item(1, "item one", "not done", "0000-00-00", "2023-03-03", "0000-00-00");
		int itemID = 1;
		
		when(itemService.getItemById(1)).thenReturn(item1);
		
		ResponseEntity<Item> res = itemController.getItemById(itemID);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(itemID, res.getBody().getId());
	}
	
}
