package com.application.views.main;

import java.util.Calendar;

import org.springframework.util.StringUtils;

import com.application.SQL.Book.Book;
import com.application.SQL.Book.BookEditor;
import com.application.SQL.Book.BookRepository;
import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Book Management")
@Route(value="GridBook", layout = MainMenu.class)

public class MySQLGridBook extends VerticalLayout {
	
	final Grid<Book> grid;
    final TextField filter;
    private final BookRepository repo;
    private final Button addNewBtn;
    private final BookEditor editor;
    
	    public MySQLGridBook(BookRepository repo, BookEditor editor) {
	        this.repo = repo;
	        this.editor = editor;
	        this.grid = new Grid<>(Book.class);
	        this.filter = new TextField();
	        this.addNewBtn = new Button
	            ("Add Book",VaadinIcon.PLUS.create());
	        addNewBtn.addThemeVariants
	         (ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

	        // build layout
	        HorizontalLayout actions = new
	                HorizontalLayout(filter, addNewBtn);
	        add(actions, grid, editor);
	        grid.setHeight("300px");
	        grid.setColumns("id", "title", "autor", "year", "isbn", "price");
	        grid.getColumnByKey("id").setWidth("120px").
	                setFlexGrow(0);
	        filter.setPlaceholder("Filter by autor");

	        // Hook logic to components
	        /* Replace listing with filtered content when user
	          changes filter*/
	        filter.setValueChangeMode(ValueChangeMode.EAGER);
	        filter.addValueChangeListener
	                 (e -> listBook(e.getValue()));

	        /* Connect selected User to editor or hide if none
	            is selected */
	        grid.asSingleSelect().addValueChangeListener(e -> {
	            editor.editBook(e.getValue());
	        });

	        /* Instantiate and edit new
	        User the new button is clicked
	         */
	        addNewBtn.addClickListener(e -> editor.editBook
	                (new Book("", "", null, null, null)));

	        // Listen changes made by the editor,
	        // refresh data from backend
	        editor.setChangeHandler(() -> {
	            editor.setVisible(false);
	            listBook(filter.getValue());
	        });

	        // Initialize listing
	        listBook(null);
	    }

	    void listBook(String filterText) {
	        if (StringUtils.isEmpty(filterText)) {
	            grid.setItems(repo.findAll());
	        } else {
	            grid.setItems(repo.
	            	findByAutorStartsWithIgnoreCase(filterText));
	        }
	    }
}