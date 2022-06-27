package com.example.application.views.main;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainMenu extends AppLayout { 

    public MainMenu() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames("text-l", "m-m");

        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(), 
          logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header); 

    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("MainView", MainView.class); 
        listLink.setHighlightCondition(HighlightConditions.sameLocation()); 
        
        RouterLink listLink2 = new RouterLink("MainView2", MainView2.class); 
        listLink2.setHighlightCondition(HighlightConditions.sameLocation()); 
        
        RouterLink listLink3 = new RouterLink("GridCsvImport", GridCsvImport.class); 
        listLink3.setHighlightCondition(HighlightConditions.sameLocation()); 

        addToDrawer(new VerticalLayout( 
            listLink,listLink2,listLink3
        ));
    }
}