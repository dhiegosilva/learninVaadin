package com.application.views.main;

import com.application.navigationbar.MainMenu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Test")
@Route(value = "test", layout = MainMenu.class)
public class MainView2 extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public MainView2() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setHorizontalComponentAlignment(Alignment.START, name, sayHello);

        add(name, sayHello);
       
    }

}