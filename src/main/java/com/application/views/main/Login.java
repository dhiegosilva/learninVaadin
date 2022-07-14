package com.application.views.main;


import java.io.File;
import com.application.video.Video2;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle("Degussa")
@Route(value = "")


public class Login extends VerticalLayout {
	
	private Button enterBtn = new Button("Enter");
    private Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "placeholder plant");
    private Video2 video = new Video2();
    private Button toggleThemeButton = new Button("Dark theme");

    public Login() {


        
    	video.setId("degussaVideo");
//    	video.setHeight("360px");
//    	video.setWidth("480px");
        video.setMaxHeight(100, Unit.PERCENTAGE);
        video.setMaxWidth(100, Unit.PERCENTAGE);
        video.setAutoPlay(true);
        video.setLoop(true);
        video.setControls(true);
//        video.setSource("https://github.com/dhiegosilva/learninVaadin/blob/main/src/main/resources/videoplayback.webm?raw=true");
//        video.setSource(new File("C:/Users/silvdx/git/learninVaadin/frontend/videoplayback.webm")); //ok
        video.setSource(new File("./frontend/videoplayback.webm"));
      
      
        img.setId("degussaLogo");

        
        enterBtn.addClickListener(e ->
        enterBtn.getUI().ifPresent(ui ->
                   ui.navigate("GridBook"))
        );
        enterBtn.addClickShortcut(Key.ENTER);

        enterBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    
        toggleThemeButton.addClickListener(click->{
    	    ThemeList themeList = UI.getCurrent().getElement().getThemeList(); // (1)

    	          if (themeList.contains(Lumo.DARK)) { // (2)
    	            themeList.remove(Lumo.DARK);
    	            toggleThemeButton.setText("Dark Theme");    
    	          } else {
    	            themeList.add(Lumo.DARK);
    	            toggleThemeButton.setText("Light Theme");    
    	          }
    	    });

      setSizeFull();
      setJustifyContentMode(JustifyContentMode.CENTER);
      setDefaultHorizontalComponentAlignment(Alignment.CENTER);
      getStyle().set("text-align", "center");
        
      add(video, img, enterBtn, toggleThemeButton);
        
    }
}
