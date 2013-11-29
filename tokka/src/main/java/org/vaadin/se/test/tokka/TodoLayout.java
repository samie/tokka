package org.vaadin.se.test.tokka;

import org.vaadin.se.test.tokka.data.Item;
import org.vaadin.se.test.tokka.data.TodoList;

import com.vaadin.addon.touchkit.extensions.TouchKitIcon;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Switch;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class TodoLayout extends NavigationView {

	VerticalComponentGroup listView = new VerticalComponentGroup();
	private TextField textField;
	private Button add;
	private TodoList todoList;
	private boolean showAll;
	private boolean showDone;

	public TodoLayout(TodoList list) {
		this(list, true, true);
	}

	public TodoLayout(TodoList list, boolean showAll, boolean showDone) {
		this.todoList = list;
		this.showAll = showAll;
		this.showDone = showDone;

		HorizontalLayout l = new HorizontalLayout();
		l.setWidth("100%");
		textField = new TextField();
		l.addComponent(textField);
		l.setExpandRatio(textField, 1f);
		textField.setSizeFull();
		l.setMargin(true);
		add = new Button();
		TouchKitIcon.plusSign.addTo(add);
		add.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Item i = new Item(textField.getValue());
				todoList.add(i);
				refresh();
			}
		});
		l.addComponent(add);

		refresh();

		CssLayout content = new CssLayout(l, listView);
		setContent(content);

	}

	public void refresh() {
		listView.removeAllComponents();
		for (Item i : todoList.getItems()) {
			if ((showAll) || (showDone && i.isDone())
					|| (!showDone && !i.isDone())) {
				Switch sw = new Switch(i.getTitle(), i.isDone());
				listView.addComponent(sw);
			}
		}

	}

}
