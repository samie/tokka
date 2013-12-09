package org.vaadin.se.test.tokka;

import org.vaadin.se.test.tokka.data.Item;
import org.vaadin.se.test.tokka.data.TodoList;

import com.vaadin.addon.touchkit.extensions.TouchKitIcon;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton.NavigationButtonClickListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class TodoListView extends NavigationView {

	private VerticalComponentGroup content;
	protected boolean editable;
	private Button editButton;

	public TodoListView() {
		setCaption("Tokka");

		content = new VerticalComponentGroup();
		editButton = new Button("Edit", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				editable = !editable;
				refresh();
			}
		});
		setRightComponent(editButton);
		setContent(content);
		refresh();
	}

	private void refresh() {
		if (editable) {
			editButton.setCaption("Done");
			content.removeAllComponents();

			final Button add = new Button("+ New list");
			add.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					final TextField tf = new TextField();
					tf.setSizeFull();
					Button add2 = new Button();
					TouchKitIcon.plus.addTo(add2);
					add2.setHeight("100%");
					HorizontalLayout lo = new HorizontalLayout(tf, add2);
					lo.setMargin(false);
					lo.setSpacing(false);
					lo.setExpandRatio(tf, 1f);
					lo.setSizeFull();
					final Popover pop = new Popover(lo);
					pop.setWidth("95%");
					pop.setHeight("55px");
					add2.addClickListener(new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							TodoList.addList("" + tf.getValue());
							pop.close();
							refresh();
						}
					});
					pop.showRelativeTo(add);
				}
			});
			add.setSizeFull();
			content.addComponent(add);

			VerticalComponentGroup g = new VerticalComponentGroup();
			for (final TodoList list : TodoList.getAllLists(true)) {
				
				Button button = new Button();
				TouchKitIcon.removeCircle.addTo(button);

				final TextField text = new TextField();
				text.setValue(list.getTitle());
				text.setSizeFull();
				text.setImmediate(true);
				text.addValueChangeListener(new ValueChangeListener() {
					
					@Override
					public void valueChange(ValueChangeEvent event) {
						list.setTitle(text.getValue());
					}
				});;
				
				HorizontalLayout hl = new HorizontalLayout(button,text);	
				hl.setWidth("100%");
				hl.setExpandRatio(text, 1f);
				button.addClickListener(new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						TodoList.removeList(list);
						refresh();
					}
				});
				content.addComponent(hl);

			}
			content.addComponent(g);

		} else {
			editButton.setCaption("Edit");
			content.removeAllComponents();
			for (final TodoList list : TodoList.getAllLists(true)) {
				NavigationButton button = new NavigationButton(list.getTitle());
				button.setDescription(list.coutUndoneItems() + "/"
						+ list.countAll());
				button.addClickListener(new NavigationButtonClickListener() {
					@Override
					public void buttonClick(NavigationButtonClickEvent event) {
						getNavigationManager().navigateTo(new TodoView(list));
					}
				});
				content.addComponent(button);

			}

		}

	}

	protected void makeEditable() {
		// TODO Auto-generated method stub
	}

}
