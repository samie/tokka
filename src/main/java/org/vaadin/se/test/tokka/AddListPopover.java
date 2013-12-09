package org.vaadin.se.test.tokka;

import org.vaadin.se.test.tokka.data.TodoList;

import com.vaadin.addon.touchkit.extensions.TouchKitIcon;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class AddListPopover extends Popover {
	    public AddListPopover() {
	        setWidth("100%");

			final TextField tf = new TextField();
			tf.setSizeFull();
			Button add2 = new Button();
			TouchKitIcon.plus.addTo(add2);
			HorizontalLayout lo = new HorizontalLayout(tf,add2);
			lo.setExpandRatio(tf, 1f);
			lo.setSizeFull();
			final Popover pop = new Popover(lo);
			add2.addClickListener(new Button.ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					TodoList.getAllLists(true).add(new TodoList(""+tf.getValue()));
					pop.close();
				}
			});

	        
	        CssLayout c = new CssLayout();
	        setContent(c);
	    }
	}