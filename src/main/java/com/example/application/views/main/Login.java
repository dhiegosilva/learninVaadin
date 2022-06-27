package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import NavigationBar.MainMenu;

@PageTitle("Test")
@Route(value = "", layout = MainMenu.class)
public class Login extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public Login() {
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