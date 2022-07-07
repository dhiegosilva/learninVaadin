package com.application.views.main;

import com.application.SQL.Person;
import com.application.SQL.PersonService;
import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
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
        new Person(1, "Dhiego Silva", "123"),
        new Person(2, "Galileo Galilei", "123"),
        new Person(3, "Johannes Kepler", "342"));
		
		Grid<Person> grid = new Grid<>();
		grid.setItems(people);
	
		grid.addColumn(Person::getName).setHeader("Name");
		grid.addColumn(Person::getLastName).setHeader("Last Name");
//		
//		
//		grid.setSelectionMode(SelectionMode.SINGLE);
//		SingleSelect<Grid<Person>, Person> personSelect =
//		        grid.asSingleSelect();
//		personSelect.addValueChangeListener(e -> {
//		    Person selectedPerson = e.getValue();
//		});
//		
	    add(grid);
	    
	    Dialog dialog = new Dialog();
        dialog.getElement()
                .setAttribute("aria-label", "System maintenance hint");
        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);
        
	    Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
	    closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
	    dialog.getHeader().add(closeButton);
	    
        Button button = new Button("Show dialog", e -> dialog.open());
	    add(dialog, button);
	    
	    PersonService a = new PersonService(); 
//	    List<Person> customers = a.findAll();
//	    Grid<Person> grid2 = new Grid<>();
//		grid2.setItems(customers);
//		grid2.addColumn(Person::getId).setHeader("Id");
//		grid2.addColumn(Person::getName).setHeader("Name");
//		grid2.addColumn(Person::getLastName).setHeader("Last Name");
//	    add(grid2);
	    
	}
	
	
	private static VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("System maintenance");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        Paragraph paragraph = new Paragraph(
                "System maintenance will begin at 3 PM. It is schedule to conclude at 5PM. We apologize for any inconvenience.");

        Button closeButton = new Button("Close");
        closeButton.addClickListener(e -> dialog.close());

        VerticalLayout dialogLayout = new VerticalLayout(headline, paragraph,
                closeButton);
        dialogLayout.setPadding(false);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }
}