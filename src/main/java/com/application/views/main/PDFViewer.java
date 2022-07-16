package com.application.views.main;

import org.vaadin.alejandro.PdfBrowserViewer;

import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@PageTitle("My Curriculum")
@Route(value = "MainView", layout = MainMenu.class)
public class PDFViewer extends HorizontalLayout {

//    private TextField name;
//    private Button sayHello;
//    private Button link;

    public PDFViewer() {
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        sayHello.addClickShortcut(Key.ENTER);
//
//        link = new Button("Navigate to company");
//        link.addClickListener(e ->
//        link.getUI().ifPresent(ui ->
//                   ui.navigate("test"))
//        );
//        add(link);
//        
//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello, link);
//
//        add(name, sayHello, link);
    	StreamResource streamResource = new StreamResource(
    	        "dhiegoCV.pdf", () -> getClass().getResourceAsStream("/META-INF/resources/documents/dhiegoCV.pdf")); // file in src/main/resources/

    	PdfBrowserViewer viewer = new PdfBrowserViewer(streamResource);
    	viewer.setHeight("720px");
    	viewer.setWidth("50%");

    	add(viewer);
    	
   
    }

}
