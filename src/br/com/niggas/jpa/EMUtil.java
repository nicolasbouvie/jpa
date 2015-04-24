package br.com.niggas.jpa;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
	private EntityManager em;
	private static class InstanceHolder {
		private static EMUtil instance = new EMUtil();
	}
	private EMUtil() {
		Map<String, Object> props = new HashMap<>();
		props.put("eclipselink.ddl-generation", "create-tables");

		clearDB();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa", props);
		this.em = emf.createEntityManager();
	}
	
	private void clearDB() {
		File folder = new File(getClass().getResource("/").getFile() + "../../");
		for (File file : folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("MyDB");
			}
		})) {
			file.delete();
		}
	}
	
	public static EntityManager getEntityManager() {
		return InstanceHolder.instance.em;
	}
	
	public static boolean doTransaction(Runnable exec, boolean commit) {
		getEntityManager().getTransaction().begin();
		try {
			exec.run();
			getEntityManager().flush();
		} catch (Exception e) {
			commit = false;
		} 
		if (commit) {
			getEntityManager().getTransaction().commit();
		} else {
			getEntityManager().getTransaction().rollback();
		}
		return commit;
	}
}
