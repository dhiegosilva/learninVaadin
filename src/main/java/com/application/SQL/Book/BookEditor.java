package com.application.SQL.Book;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component
                          .orderedlayout.HorizontalLayout;
import com.vaadin.flow.component
                           .orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class BookEditor extends VerticalLayout 
                          implements KeyNotifier {

    private final BookRepository repository;
    /* Fields to edit properties in Book entity */
    TextField title = new TextField("Title");
    TextField autor = new TextField("Autor");
    DatePicker year = new DatePicker("Release Date");
    TextField isbn = new TextField("ISBN");
    TextField price = new TextField("Price");
    /* Action buttons */
    Button save = new Button
        ("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button
        ("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout
                        (save, cancel, delete);
    Binder<Book> binder = new Binder<>(Book.class);
    private Book book;
    private ChangeHandler changeHandler;

    @Autowired
    public BookEditor(BookRepository repository) {
        this.repository = repository;
        year.setLocale(getLocale());
        add(title, autor, year, isbn, price, actions);
//        Calendar cal = Calendar.getInstance();
//        cal.set(1999, 1, 1);
//        Date date = cal.getTime()
        //repository.save(new Book("Harry Potter", "JK Rowling", date, "ISBN123123213", 19.99));

        // bind using naming convention
        binder.bindInstanceFields(this);
        // Configure and style components
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        addKeyPressListener(Key.ENTER, e -> save());
        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editBook(book));
        setVisible(false);
    }

    void delete() {
        repository.delete(book);
        changeHandler.onChange();
    }

    void save() {
        repository.save(book);
        changeHandler.onChange();
    }

    public final void editBook(Book usr) {
        if (usr == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = usr.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
        	book = repository.findById(usr.getId()).get();
        } else {
        	book = usr;
        }
        cancel.setVisible(persisted);
        /* Bind user properties to similarly named fields
         Could also use annotation or "manual binding" 
         or programmatically
         moving values from fields to entities before saving*/
        binder.setBean(book);

        setVisible(true);

        // Focus first name initially
        title.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        /* ChangeHandler is notified when either save or delete
         is clicked*/
        changeHandler = h;
    }

    public interface ChangeHandler {
        void onChange();
    }
}