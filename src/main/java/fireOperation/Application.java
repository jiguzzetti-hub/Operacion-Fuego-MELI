package fireOperation;

import java.util.stream.Stream;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;

@SpringBootApplication
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        var dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        dsv.setValidationQuery(DatabaseDriver.POSTGRESQL.getValidationQuery());
        return dsv;
    }
	
	@Bean
	public static BeanFactoryPostProcessor dependsOnPostProcessor() {
	    return bf -> {
//	    		Let beans that need the database depend on the DatabaseStartupValidator
//	    		like the JPA EntityManagerFactory or Flyway
//	        String[] flyway = bf.getBeanNamesForType(Flyway.class);
//	        Stream.of(flyway)
//	                .map(bf::getBeanDefinition)
//	                .forEach(it -> it.setDependsOn("databaseStartupValidator"));

	        String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
	        Stream.of(jpa)
	                .map(bf::getBeanDefinition)
	                .forEach(it -> it.setDependsOn("databaseStartupValidator"));
	    };
	}
	
	
}
