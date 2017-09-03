/**
 * 
 */
package co.in.hawker.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Siva
 * 
 */
@Component
public class DBInitializer {
	private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

	@Value("${init-db:false}")
	private String initDatabase;

	@PostConstruct
	public void init() {
		try {
			logger.info("############## InitDatabase :" + initDatabase + " ########################");
			if (Boolean.parseBoolean(initDatabase)) {
				initDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void initDatabase() {
		logger.info("Initializing Database with sample data");
	}
}
