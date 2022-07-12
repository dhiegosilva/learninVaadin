package com.application.views.main;

import java.io.File;

import com.application.video.Video;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Degussa")
@Route(value = "")

public class Login extends VerticalLayout {
	
	private Button enterBtn = new Button("Enter");
    private Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "placeholder plant");
    private Video video = new Video();
    
    public Login() {
		
    	video.setId("degussaVideo");
    	video.setAutoPlay(true);
    	video.setLoop(true);
        video.setControls(false);
        
//        String path = "C:/Users/silvdx/git/learninVaadin/src/main/resources/videoplayback.mp4";
//        File filename = new File(path);
//        
//        video.setSource(filename);

        video.setSource("https://github.com/dhiegosilva/learninVaadin/blob/main/src/main/resources/videoplayback.mp4?raw=true");
	
    	add(video);
    	
        img.setId("degussaLogo");
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
        
        enterBtn.addClickListener(e ->
        enterBtn.getUI().ifPresent(ui ->
                   ui.navigate("GridBook"))
        );
        enterBtn.addClickShortcut(Key.ENTER);

        enterBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	    
        add(img, enterBtn);
        
    }

}
