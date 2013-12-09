package org.vaadin.se.test.tokka.data;

public class Item {

	private boolean done;
	private String title;

	public Item(String title, boolean done) {
		this.title = title;
		this.done = done;
	}

	public Item(String title) {
		this(title,false);
	}

	public boolean isDone() {
		return done;
	}

	public String getTitle() {
		return this.title;
	}

	
}
