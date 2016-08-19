package com.organization.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

/**
 * class to configure cassandra
 */
@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraUtil {

	private static final String KEYSPACE = "cassandra.keyspace";
	private static final String CONTACTPOINTS = "cassandra.contactpoints";
	private static final String PORT = "cassandra.port";

	@Autowired
	private Environment enviroment;

	public CassandraUtil() {
	}

	/**
	 * get keyspace for cassandra
	 * 
	 * @return {@link String}
	 */
	private String getKeySpaceName() {
		return enviroment.getProperty(KEYSPACE);
	}

	/**
	 * get contact point for cassandra
	 * 
	 * @return {@link String}
	 */
	private String getContactPoints() {
		return enviroment.getProperty(CONTACTPOINTS);
	}

	/**
	 * get port for cassandra
	 * 
	 * @return {@link Int}
	 */
	private int getPortNumber() {
		return Integer.parseInt(enviroment.getProperty(PORT));
	}

	/**
	 * setup cluster for cassandra
	 * 
	 * @return {@link CassandraClusterFactoryBean}
	 */
	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(getContactPoints());
		cluster.setPort(getPortNumber());
		return cluster;
	}

	/**
	 * setup Mapping context
	 * 
	 * @return {@link CassandraMappingContext}
	 */
	@Bean
	public CassandraMappingContext mappingContext() {
		return new BasicCassandraMappingContext();
	}

	/**
	 * set up converter
	 * 
	 * @return {@link CassandraConverter}
	 */
	@Bean
	public CassandraConverter converter() {
		return new MappingCassandraConverter(mappingContext());
	}

	/**
	 * build session for cassandra
	 * 
	 * @return {@link CassandraSessionFactoryBean}
	 * @throws Exception
	 */
	@Bean
	public CassandraSessionFactoryBean session() throws Exception {
		CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
		cassandraSessionFactoryBean.setCluster(cluster().getObject());
		cassandraSessionFactoryBean.setKeyspaceName(getKeySpaceName());
		cassandraSessionFactoryBean.setConverter(converter());
		cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
		return cassandraSessionFactoryBean;
	}

	/**
	 * 
	 * @return {@link CassandraOperations}
	 * @throws Exception
	 */
	@Bean
	public CassandraOperations cassandraTemplate() throws Exception {
		return new CassandraTemplate(session().getObject());
	}

}
