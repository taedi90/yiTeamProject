package kr.or.yi.teamProject.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"kr.or.yi.teamProject"})
@MapperScan(basePackages = {
        "kr.or.yi.teamProject.user.mapper",
        "kr.or.yi.teamProject.manage.mapper",
        "kr.or.yi.teamProject.order.mapper",
        "kr.or.yi.teamProject.payment.mapper",
        "kr.or.yi.teamProject.product.mapper"})
@EncryptablePropertySource(name = "EncryptedProperties", value = "classpath:/config.properties")
//@PropertySource("classpath:/config.properties")
public class RootConfig {

    @Value("${db.driverClassName}")
    private String dsDriver;
    @Value("${db.username}")
    private String dsUsername;
    @Value("${db.password}")
    private String dsPassword;

    // 커넥션 pool을 사용할 dataSource bean
//    @Bean("dataSource")
//    public DataSource dataSource() {
//
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(dsDriver);
//        dataSource.setUrl(dsUrl);
//        dataSource.setUsername(dsUsername);
//        dataSource.setPassword(dsPassword);
//
//        return dataSource;
//    }

    @Bean("dataSource")
    public DataSource dataSource() {
        Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(dataSourceSpied());

        Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
        formatter.setLoggingType(LoggingType.MULTI_LINE);
        formatter.setSqlPrefix("SQL         :  ");

        dataSource.setLogFormatter(formatter);
        return dataSource;
    }

    @Bean("dataSourceSpied")
    public DataSource dataSourceSpied() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dsDriver);
        dataSource.setUrl(System.getenv("JDBC_URL"));
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
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
