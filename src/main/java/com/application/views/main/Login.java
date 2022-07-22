package com.application.views.main;


import java.io.File;

import javax.annotation.security.PermitAll;

import com.github.olafj.vaadin.flow.Video;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle("Degussa")
@Route(value = "login")

public class Login extends VerticalLayout {
	
	private Button enterBtn = new Button("Enter");
    private Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "placeholder plant");
    private Video video = new Video();
    private Button toggleThemeButton = new Button("Dark theme");

    public Login() {
        
    	video.setId("degussaVideo");
        video.setMaxHeight(100, Unit.PERCENTAGE);
        video.setMaxWidth(100, Unit.PERCENTAGE);
        video.setAutoPlay(true);
        video.setLoop(true);
        video.setControls(true);
//        video.setSource("https://github.com/dhiegosilva/learninVaadin/blob/main/src/main/resources/videoplayback.webm?raw=true");
//        video.setSource(new File("C:/Users/silvdx/git/learninVaadin/frontend/videoplayback.webm")); //ok
//        video.setSource(new File("./frontend/videoplayback.webm"));
        
        StreamResource streamResource = new StreamResource(
    	        "dhiegoCV.pdf", () -> getClass().getResourceAsStream("/META-INF/resources/videos/videoplayback.webm")); // file in src/main/resources/

        video.setSource(streamResource);
        img.setId("degussaLogo");

//        enterBtn.addClickListener(event -> {
//        		Notification.show("Thanks Degussa, from Dhiego Silva").setPosition(Position.TOP_CENTER);
//        		getUI().ifPresent(ui -> ui.navigate("GridBook"));
//          });
        
        
        
        LoginOverlay loginOverlay = new LoginOverlay();
        
        
        LoginI18n i18n = LoginI18n.createDefault();

		LoginI18n.Form i18nForm = i18n.getForm();
		i18nForm.setTitle("Credentials");
		i18nForm.setUsername("User ('user')");
		i18nForm.setPassword("Password ('userpass')");
		i18nForm.setSubmit("Enter");
		i18nForm.setForgotPassword("Forgot Password");
		i18n.setForm(i18nForm);

		LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
		i18nErrorMessage.setTitle("Error Message");
		i18nErrorMessage.setMessage("Access Denied");
		i18n.setErrorMessage(i18nErrorMessage);

		loginOverlay.setI18n(i18n);
		loginOverlay.setTitle("Degussa Portal");
		loginOverlay.setDescription("Built with Vaadin");
		
        
        enterBtn.addClickListener(event -> loginOverlay.setOpened(true));
        enterBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        enterBtn.addClickShortcut(Key.ENTER);
        loginOverlay.setAction("login");
        
                
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
        
      add(video, img, loginOverlay, enterBtn, toggleThemeButton);
        
    }
}
