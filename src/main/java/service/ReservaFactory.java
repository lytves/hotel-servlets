package service;


/**
 * Session Bean implementation class ReservaFactory
 */
public class ReservaFactory {	
	
    /**
     * Default constructor. 
     */
    public ReservaFactory() {
        // TODO Auto-generated constructor stub
    }
    
	public static ReservaServiceFacade newInstance(){
		ReservaServiceFacade reservaservice;
	    reservaservice = new ReservaServiceFacadeImpl();
	    	
	    //Devuelve un objeto del tipo de la interfaz
	    return reservaservice;

	}

}
