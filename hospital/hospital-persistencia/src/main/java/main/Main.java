package main;

import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) throws Exception{

		Persistence.createEntityManagerFactory("jpa_pu").createEntityManager();
	}

}
