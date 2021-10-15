package com.example.demo.config;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class APIMessages {

	public APIMessages() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param typeMsg 	: Default=SEVERITY_INFO; {0=SEVERITY_INFO;1=SEVERITY_ERROR;2=SEVERITY_FATAL;3=SEVERITY_WARN;}
	 * @param titleMsg 	: Message Title
	 * @param detailMsg : Detail Message 
	 */
	public static void handleMessage(int typeMsg, String titleMsg,
			String detailMsg) {
		FacesMessage.Severity typeMessage = FacesMessage.SEVERITY_INFO;
		typeMessage = typeSeverity(typeMsg);
		FacesMessage msg = new FacesMessage(typeMessage, titleMsg, detailMsg);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * 
	 * @param option : Default=SEVERITY_INFO; {0=SEVERITY_INFO;1=SEVERITY_ERROR;2=SEVERITY_FATAL;3=SEVERITY_WARN;}
	 * @return FacesMessage.Severity
	 */
	public static FacesMessage.Severity typeSeverity(int option) {
		FacesMessage.Severity resultSeverity = FacesMessage.SEVERITY_INFO;
		switch (option) {
		case 0:
			resultSeverity = FacesMessage.SEVERITY_INFO;
			break;
		case 1:
			resultSeverity = FacesMessage.SEVERITY_ERROR;
			break;
		case 2:
			resultSeverity = FacesMessage.SEVERITY_FATAL;
			break;
		case 3:
			resultSeverity = FacesMessage.SEVERITY_WARN;
			break;
		default:
			resultSeverity = FacesMessage.SEVERITY_INFO;
			break;
		}
		return resultSeverity;
	}
}
