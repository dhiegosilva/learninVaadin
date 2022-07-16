package com.application.navigationbar;

import com.application.views.main.GridCsvImport;
import com.application.views.main.PDFViewer;
import com.application.views.main.MySQLGridBook;
import com.application.views.main.MySQLGridUsers;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.Lumo;

public class MainMenu extends AppLayout { 

    public MainMenu() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
    	Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "degussa");
    	img.setWidth("100px");

        HorizontalLayout header = new HorizontalLayout(
          new DrawerToggle(), 
          img
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

    
        Button toggleButton = new Button("Dark theme");
        toggleButton.setMinWidth(40,Unit.MM);
	    toggleButton.addClickListener(click->{
	    ThemeList themeList2 = UI.getCurrent().getElement().getThemeList(); // (1)
	
	          if (themeList2.contains(Lumo.DARK)) { // (2)
	            themeList2.remove(Lumo.DARK);
	            toggleButton.setText("Dark Theme");    
	          } else {
	            themeList2.add(Lumo.DARK);
	            toggleButton.setText("Light Theme");    
	          }
	    });
    
        if(UI.getCurrent().getElement().getThemeList().contains(Lumo.DARK))
        {
            toggleButton.setText("Light Theme");    
        }
	    
	    addToNavbar(header, toggleButton); 

}


    private void createDrawer() {
        RouterLink pdfViewer = new RouterLink("My Curriculum", PDFViewer.class); 
        pdfViewer.setHighlightCondition(HighlightConditions.sameLocation()); 
        
        RouterLink userDBMgmtLink = new RouterLink("Users DB Management", MySQLGridUsers.class); 
        userDBMgmtLink.setHighlightCondition(HighlightConditions.sameLocation());
        
        RouterLink bookLink = new RouterLink("Book DB Management", MySQLGridBook.class); 
        bookLink.setHighlightCondition(HighlightConditions.sameLocation()); 
        
        RouterLink csvImportLink = new RouterLink("Csv Import Grid", GridCsvImport.class); 
        csvImportLink.setHighlightCondition(HighlightConditions.sameLocation()); 

        addToDrawer(new VerticalLayout( 
        	bookLink, userDBMgmtLink, csvImportLink, pdfViewer
        ));
    }
}