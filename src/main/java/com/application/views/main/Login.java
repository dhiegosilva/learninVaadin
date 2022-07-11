package com.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Image;
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
		
		
//		IFrame iFrame = new IFrame("https://www.youtube.com/embed/7Jxdj0tp-Z0?autoplay=1&mute=1&list=PL9W3j9MEM_1bM5GeGirsiwTHZk0yCvw-f&loop=1");
//		IFrame iFrame = new IFrame("https://www.youtube.com/embed/QH2-TGUlwu4?vq=hd1080&autoplay=1&fs=0&controls=0&disablekb=1");
//		iFrame.setHeight("315px");
//        iFrame.setWidth("560px");
//        iFrame.setAllow("autoplay=1");<iframe frameborder="0" scrolling="no" marginheight="0" marginwidth="0"width="788.54" height="443" type="text/html" src="https://www.youtube.com/embed/7Jxdj0tp-Z0?autoplay=1&fs=0&iv_load_policy=3&showinfo=0&rel=0&cc_load_policy=0&start=0&end=0&vq=hd1080&origin=http://youtubeembedcode.com"><div><small><a href="https://youtubeembedcode.com/en/">https://youtubeembedcode.com/en/</a></small></div><div><small><a href="https://spelsajterutansvensklicens.se/">spelsajterutansvensklicens.se</a></small></div><div><small><a href="https://youtubeembedcode.com/en/">youtubeembedcode en</a></small></div><div><small><a href="https://xn--bsta-utlndska-casinon-51bh.se/">https://xn--bsta-utlndska-casinon-51bh.se/</a></small></div></iframe>
//        iFrame.setAllow("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture");
//        iFrame.getElement().setAttribute("controls", "0");
//        iFrame.getElement().setAttribute("disablekb", true);
//        iFrame.getElement().setAttribute("allowfullscreen", false);
//        iFrame.getElement().setAttribute("frameborder", "0");
//		add(iFrame);
		
		
		/*<iframe width="560" height="315" src="https://www.youtube.com/embed/7Jxdj0tp-Z0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		 * */
		 
		
		
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

}
