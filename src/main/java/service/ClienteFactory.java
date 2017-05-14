package service;
 

/**
 * Session Bean implementation class ClienteFactory
 */
public class ClienteFactory {	
	
    /**
     * Default constructor. 
     */
    public ClienteFactory() {
        // TODO Auto-generated constructor stub
    }
    
	public static ClienteServiceFacade newInstance(){
		ClienteServiceFacade clienteservice;
		clienteservice = new ClienteServiceFacadeImpl();
	    	
	    //Devuelve un objeto del tipo de la interfaz
	    return clienteservice;

	}

}
