package com.application.views.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.util.StringUtils;
import org.vaadin.olli.FileDownloadWrapper;

import com.application.SQL.Book.Book;
import com.application.SQL.Book.BookEditor;
import com.application.SQL.Book.BookRepository;
import com.application.navigationbar.MainMenu;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@PageTitle("Book Management")
@Route(value="GridBook", layout = MainMenu.class)

public class MySQLGridBook extends VerticalLayout {
	
	final Grid<Book> grid;
    final TextField filter;
    private final BookRepository repo;
    private final Button addNewBtn;
    private final BookEditor editor;
        
	    public MySQLGridBook(BookRepository repo, BookEditor editor) {
	    	
	    	MemoryBuffer buffer = new MemoryBuffer();
	        Upload upload = new Upload(buffer);
	        upload.setAcceptedFileTypes(".csv");
	        upload.addSucceededListener(e -> {
	            importCsv(buffer.getInputStream());
	        });
	    	
	        this.repo = repo;
	        this.editor = editor;
	        this.grid = new Grid<>(Book.class);
	        this.filter = new TextField();
	        this.addNewBtn = new Button
	            ("Add Book",VaadinIcon.PLUS.create());
	        addNewBtn.addThemeVariants
	         (ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

	        
	        //download example
	        Button button = new Button("Download Import Template");
	        FileDownloadWrapper buttonWrapper = new FileDownloadWrapper(
	                new StreamResource(
	                        "BookUploadTemplate.csv", () -> getClass().getResourceAsStream("/META-INF/resources/documents/BookUploadTemplate.csv")));
	        buttonWrapper.wrapComponent(button);
	        // build layout
	        VerticalLayout upDownload = new
	        		VerticalLayout(upload, buttonWrapper);
	        
	        HorizontalLayout actions = new
	                HorizontalLayout(filter, addNewBtn);
	        add(upDownload, actions, grid, editor);
	        grid.setHeight("300px");
	        grid.removeAllColumns();
	        //grid.setColumns("id", "title", "autor", "year", "isbn");
//	        grid.addColumn(Book::getId).setHeader("Id").setWidth("60px").setFlexGrow(0);

	        grid.addColumn(Book::getTitle).setHeader("Title").setSortable(true);
	        
	        grid.addColumn(Book::getAutor).setHeader("Autor").setSortable(true);
	        	        
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	        grid.addColumn(bean -> formatter.format(bean.getYear())).setHeader("Release Year").setSortable(true);
	        
	        grid.addColumn(Book::getIsbn).setHeader("ISBN").setSortable(true);

	        grid.addColumn(new NumberRenderer<>(
	                Book::getPrice, "EUR %(,.2f",
	                Locale.GERMANY, "EUR 0,00")
	        ).setHeader("Price");
	        

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
	        if (!StringUtils.hasLength(filterText)) {
	            grid.setItems(repo.findAll());
	        } else {
	            grid.setItems(repo.
	            	findByAutorStartsWithIgnoreCase(filterText));
	        }
	    }
	    
	    private void importCsv(InputStream resourceAsStream) {
	        // Change the separator if needed to something else (for example, to ',').
	        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
	        CSVReader reader =
	                new CSVReaderBuilder(new InputStreamReader(resourceAsStream)).withCSVParser(parser).build();
            try {
				List<String[]> entries = reader.readAll();
				String[] headers = entries.get(0);
				
				for(int i = 0; i < entries.size(); i++) {
					String[] strings = entries.get(i);
					
					String sDate1=strings[2]; 
					SimpleDateFormat formatter1=new SimpleDateFormat("dd.MM.yyyy");
					Date date1 =formatter1.parse(sDate1);
					
					repo.save(new Book(strings[0], strings[1], date1, strings[3], Double.parseDouble(strings[4])));
		        }
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CsvException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         // Initialize listing
	        listBook(null);
	    }
}