package kr.or.yi.teamProject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"kr.or.yi.teamProject"})
@MapperScan(basePackages = {"kr.or.yi.teamProject"})
@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:/config.properties")
//@PropertySource("classpath:/config.properties")
public class RootConfig {

    @Value("${db.driverClassName}")
    private String dsDriver;
    @Value("${db.url}")
    private String dsUrl;
    @Value("${db.username}")
    private String dsUsername;
    @Value("${db.password}")
    private String dsPassword;

    // 커넥션 pool을 사용할 dataSource bean
    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dsDriver);
        dataSource.setUrl(dsUrl);
        dataSource.setUsername(dsUsername);
        dataSource.setPassword(dsPassword);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        sqlSessionFactory.setConfigLocation(
                new PathMatchingResourcePatternResolver().
                        getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().
                        getResources("classpath:/sqlmap/**/*_SQL.xml"));
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
