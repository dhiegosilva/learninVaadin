package com.application.views.main;

import com.application.video.Video;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

@PageTitle("Degussa")
@Route(value = "")
//@JsModule("./js/prefers-color-scheme.js")

public class Login extends VerticalLayout {
	
    private Button toggleThemeButton = new Button("Dark theme");
	private Button enterBtn = new Button("Enter");
    private Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/5/56/Degussa_logo.png", "placeholder plant");

    public Login() {
		
		Video video = new Video("/mytodo/src/main/resources/videoplayback.webm");
        video.setMaxWidth("500px");
        add(video);
		
		
		
//		initView();
//		String iframe = "<iframe style=\"position: absolute; top: 0; left: 0; width: 100%; height: 100%;\" width=\"1280\" height=\"720\" src=\"https://www.youtube.com/embed/7Jxdj0tp-Z0?autoplay=1&mute=1&disablekb=1&controls=0&loop=1\" frameborder=\"0\"></iframe>\r\n";
//		
//		Label content = new Label();
//		content.getElement().setProperty("innerHTML", iframe);
//		add(content);
		
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
	    
        add(img, enterBtn, toggleThemeButton);
    }
	private void initView() {
		setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
	}

}
