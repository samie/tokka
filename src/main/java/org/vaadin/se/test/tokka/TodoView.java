package org.vaadin.se.test.tokka;

import org.vaadin.se.test.tokka.data.TodoList;

import com.vaadin.addon.touchkit.extensions.TouchKitIcon;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.TabBarView;
import com.vaadin.addon.touchkit.ui.TabBarView.SelectedTabChangeEvent;
import com.vaadin.addon.touchkit.ui.TabBarView.SelectedTabChangeListener;

public class TodoView extends NavigationView {

	private TodoList list;

	public TodoView(final TodoList list) {
		this.list = list;
		setCaption(list.getTitle());

		final TabBarView tabs = new TabBarView();

		final TodoLayout all = new TodoLayout(list);
		final TodoLayout undone = new TodoLayout(list, false, false);
		final TodoLayout done = new TodoLayout(list, false, true);
		tabs.addTab(all, "All");
		tabs.addTab(undone, "Todo");
		tabs.addTab(done, "Done");
		TouchKitIcon.asterisk.addTo(all);
		TouchKitIcon.checkEmpty.addTo(undone);
		TouchKitIcon.check.addTo(done);
		tabs.addListener(new SelectedTabChangeListener() {

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {

				TodoLayout t = (TodoLayout) tabs.getSelelectedTab()
						.getComponent();
				t.refresh();
			}

		});
		tabs.setSelectedTab(all);

		setContent(tabs);
	}

}
