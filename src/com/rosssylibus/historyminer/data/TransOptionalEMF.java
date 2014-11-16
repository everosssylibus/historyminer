package com.rosssylibus.historyminer.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class TransOptionalEMF {
	
	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");

	public TransOptionalEMF() {}
	
	public static EntityManagerFactory get() {
		
		return emfInstance;
		
	}
}
