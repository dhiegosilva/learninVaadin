package com.application.views.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle("Login")
@Route(value = "")
//@JsModule("./js/prefers-color-scheme.js")

public class Login extends VerticalLayout {

	public Login() {
        setSpacing(false);

        Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "placeholder plant");
        img.setWidth("200px");

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
        Button link = new Button("Enter");
        link.addClickListener(e ->
        link.getUI().ifPresent(ui ->
                   ui.navigate("test"))
        );
        link.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        Button toggleButton = new Button("Dark theme");

	    toggleButton.addClickListener(click->{
	    ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // (1)
	
	          if (themeList.contains(Lumo.DARK)) { // (2)
	            themeList.remove(Lumo.DARK);
	            toggleButton.setText("Dark Theme");    
	          } else {
	            themeList.add(Lumo.DARK);
	            toggleButton.setText("Light Theme");    
	          }
	    });
        
        add(img,link, toggleButton);
        
    }

}
