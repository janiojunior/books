package br.unitins.books.application;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {
	
	private Util() {
		// para nao permitir uma instancia
	}

	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance()
			.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					message, null));
	}
	
}
