package net.ddns.zierservices.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Msg {
	
	public static void msg(FacesMessage.Severity type, String summary) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type, summary, null));
	}
	
	public static void msg(FacesMessage.Severity type, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(type, summary, detail));
	}
}
