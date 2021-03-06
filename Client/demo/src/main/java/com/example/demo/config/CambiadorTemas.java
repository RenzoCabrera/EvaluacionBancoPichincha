package com.example.demo.config;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Component
@ViewScoped
public class CambiadorTemas implements Serializable{
	private static final long serialVersionUID = 1L;
	private String tema="excite-bike";
	//private String tema="bluesky";
	 private Map<String, String> themes;
	 
	 public void init(){
	 }
     public Map<String, String> getThemes() {
         themes = new TreeMap<String, String>();
         themes.put("afterdark", "afterdark");
         themes.put("aristo", "aristo");
         themes.put("black-tie", "black-tie");
         themes.put("blitzer", "blitzer");
         themes.put("bluesky", "bluesky");
         themes.put("casablanca", "casablanca");
         themes.put("cupertino", "cupertino");
         themes.put("dark-hive", "dark-hive");
         themes.put("dot-luv", "dot-luv");
         themes.put("eggplant", "eggplant");
         themes.put("excite-bike", "excite-bike");
         themes.put("flick", "flick");
         themes.put("glass-x", "glass-x");
         themes.put("hot-sneaks", "hot-sneaks");
         themes.put("humanity", "humanity");
         themes.put("le-frog", "le-frog");
         themes.put("midnight", "midnight");
         themes.put("mint-choc", "mint-choc");
         themes.put("none", "none");
         themes.put("overcast", "overcast");
         themes.put("pepper-grinder", "pepper-grinder");
         themes.put("redmond", "redmond");
         themes.put("rocket", "rocket");
         themes.put("sam", "sam");
         themes.put("smoothness", "smoothness");
         themes.put("south-street", "south-street");
         themes.put("start","start");
         themes.put("sunny", "sunny");
         themes.put("swanky-purse", "swanky-purse");
         themes.put("trontastic", "trontastic");
         themes.put("ui-darkness", "ui-darkness");
         themes.put("ui-lightness", "ui-lightness");
         themes.put("vader", "vader");
     return themes;
}

  
 	
     public void redireccion(){
 		try {
 			FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/cambiadorTemas.xhtml");

 		} catch (Exception e) {
 			// TODO: handle exception
 		}
 	}
     
     public void setThemes(Map<String, String> themes) {
         this.themes = themes;
     }

    public CambiadorTemas() {
    }

    public String getTema() {
        return tema;
     }

     public void setTema(String tema) {
         this.tema = tema;
     }

    public String cambiar(){
       return null;
     }

}
