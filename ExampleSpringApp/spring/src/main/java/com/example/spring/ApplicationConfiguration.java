
package com.example.spring;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.example.jcache", "com.example.spring", "com.example.spring.mvc" })
@EnableMBeanExport
@EnableTransactionManagement
@EnableCaching
public class ApplicationConfiguration {

    @Bean
    public View marshallingView() {
        return new MarshallingView(jaxb2Marshaller());
    }

    @Bean
    public Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Contact.class);
        return marshaller;
    }

    /*
     * 
    @Bean
    public ViewResolver viewResolver(){
    	return new BeanNameViewResolver();
    }
    */

    /*
     * 
     * Define @Bean when @ComponentScan is not used.
     * 
     * If @Bean is used @Component and @Autowired are not needed.
     * 
     * 
     * @Bean public PhoneService phoneService(ContactRepository repository){
     * return new PhoneServiceBean(repository); }
     * 
     * @Bean public ContactRepository repository(){ return new ContactTable(); }
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=test");
        dataSource.setUsername("sa");
        dataSource.setPassword("escape10FLEX");
        return dataSource;

        /*
         * HSQL datasource
         * 
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("test")
                .build();
                
        */
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.caching", "com.training");
        em.setPersistenceUnitName("myPersistenceUnit");
        // em.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");

        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(va);

        Properties ps = new Properties();

        ps.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        // ps.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");

        ps.put("hibernate.hbm2ddl.auto", "none");
//        ps.put("hibernate.hbm2ddl.auto", "update");
        ps.put("hibernate.show_sql", "true");

        em.setJpaProperties(ps);

        em.afterPropertiesSet();
        return em;
    }
}
