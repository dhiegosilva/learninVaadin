package com.application.views.main;

import com.application.Objects.Person;
import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.List;

@PageTitle("Personal Management")
@Route(value="GridPerson", layout = MainMenu.class)

public class GridPerson extends VerticalLayout {

	public GridPerson() {
		
		List<Person> people = Arrays.asList(
        new Person("Nicolaus Copernicus", "123"),
        new Person("Galileo Galilei", "123"),
        new Person("Johannes Kepler", "342"));
		
		Grid<Person> grid = new Grid<>();
		grid.setItems(people);
	
		grid.addColumn(Person::getName).setHeader("Name");
		grid.addColumn(Person::getLastName).setHeader("Last Name");
		
		
		grid.setSelectionMode(SelectionMode.SINGLE);
		SingleSelect<Grid<Person>, Person> personSelect =
		        grid.asSingleSelect();
		personSelect.addValueChangeListener(e -> {
		    Person selectedPerson = e.getValue();
		});
		
		
		
	    add(grid);
	}
}