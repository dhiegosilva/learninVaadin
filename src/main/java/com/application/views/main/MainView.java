package com.application.views.main;

import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("MainView")
@Route(value = "MainView", layout = MainMenu.class)
public class MainView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private Button link;

    public MainView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        link = new Button("Navigate to company");
        link.addClickListener(e ->
        link.getUI().ifPresent(ui ->
                   ui.navigate("test"))
        );
        add(link);
        
        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello, link);

        add(name, sayHello, link);
   
    }

}
