package org.vaadin.se.test.tokka.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TodoList {

	private static ArrayList<TodoList> allLists;
	private List<Item> items = new ArrayList<Item>();
	private String title;

	public TodoList(String title, Item... items) {
		this.title = title;
		for (Item item : items) {
			this.items.add(item);
		}
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

	public void add(Item item) {
		items.add(item);
	}

	public void remove(Item item) {
		items.remove(item);
	}

	public static List<TodoList> getAllLists(boolean dummy) {
		if (allLists == null) {
			allLists = new ArrayList<TodoList>();
			if (dummy) {
				fillDummyData(allLists);
			}
		}

		return allLists;
	}

	private static void fillDummyData(ArrayList<TodoList> l) {
		l.add(new TodoList("Shopping list", new Item("1"), new Item("2"),
				new Item("3", true), new Item("4", true), new Item("5", true)));
		l.add(new TodoList("Work todo", new Item("Checkout snapshot", true),
				new Item("Implement wireframe", true),
				new Item("Answer survey")));
	}

	public String getTitle() {
		return title;
	}

	public int countAll() {
		return items.size();
	}

	public int coutUndoneItems() {
		int count = 0;
		for (Item i : items) {
			count += i.isDone() ? 0 : 1;
		}
		return count;
	}

	public static void removeList(TodoList list) {
		getAllLists(false).remove(list);		
	}

	public static void addList(String string) {
		getAllLists(false).add(new TodoList(string));
	}

	public void setTitle(String value) {
		title = value;		
	}

}
