package dao;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;




public class Helper {
	public static ObjectContainer ConnectionDB(final String dataBase){
		
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dataBase);
		
		    // do something with db4o
			return db;
	}
		
}

