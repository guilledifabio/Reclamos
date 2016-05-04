package dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;




public class Helper {
	public static ObjectContainer ConnectionDB(final String dataBase){
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().add(new TransparentPersistenceSupport());
		
		ObjectContainer db = Db4oEmbedded.openFile(config, dataBase);
		
		    // do something with db4o
			return db;
	}
		
}

