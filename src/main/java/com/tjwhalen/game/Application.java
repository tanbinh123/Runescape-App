package com.tjwhalen.game;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjwhalen.game.loader.AbstractLoader;
import com.tjwhalen.game.loader.LoaderRunner;
import com.tjwhalen.game.loader.impl.LoadItems;
import com.tjwhalen.game.loader.impl.LoadPriceData;
import com.tjwhalen.game.report.AbstractReport;
import com.tjwhalen.game.report.ReportRunner;
import com.tjwhalen.game.report.impl.MarginFinder;
import com.tjwhalen.game.service.ItemSummaryDbService;
import com.tjwhalen.game.service.ItemSummaryRestService;
import com.tjwhalen.game.service.PriceDataDbService;
import com.tjwhalen.game.service.PriceDataRestService;
import com.tjwhalen.game.service.PriceDataTimeService;
import com.tjwhalen.game.service.dao.ItemSummaryMapper;
import com.tjwhalen.game.service.dao.ItemSummaryURLConsumer;
import com.tjwhalen.game.service.dao.PriceDataMapper;
import com.tjwhalen.game.service.dao.PriceDataTimeMapper;
import com.tjwhalen.game.service.dao.PriceDataURLConsumer;
import com.tjwhalen.game.service.impl.ItemSummaryDbServiceImpl;
import com.tjwhalen.game.service.impl.ItemSummaryRestServiceImpl;
import com.tjwhalen.game.service.impl.PriceDataDbServiceImpl;
import com.tjwhalen.game.service.impl.PriceDataRestServiceImpl;
import com.tjwhalen.game.service.impl.PriceDataTimeServiceImpl;
import com.tjwhalen.game.service.model.ItemSummary;
import com.tjwhalen.game.service.model.PriceData;
import com.tjwhalen.game.service.model.PriceDataTime;

@SpringBootApplication
public class Application implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Value("${Tyler.loaders}")
	String loaderNames;
	@Value("${Tyler.reports}")
	String reportNames;
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	LoaderRunner loaderRunner;
	
	@Autowired
	ReportRunner reportRunner;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		loaderRunner.setLoaders(getLoaderBeans());
		loaderRunner.execute();
		reportRunner.setReports(getReportBeans());
		reportRunner.execute();
	}
	
	private List<AbstractLoader> getLoaderBeans() {
		
		if (loaderNames == null || "".equalsIgnoreCase(loaderNames)) {
			return null;
		}
			
		ArrayList<AbstractLoader> beans = new ArrayList<AbstractLoader>();
		String[] loaderNameList = loaderNames.split(",");
		
		for (String str : loaderNameList) {
			logger.info("Loaded  loader bean " + str);
			beans.add((AbstractLoader)context.getBean(str));
		}
		return beans;
	}
	
	private List<AbstractReport> getReportBeans() {
		
		if (reportNames == null || "".equalsIgnoreCase(reportNames)) {
			return null;
		}
		
		ArrayList<AbstractReport> beans = new ArrayList<AbstractReport>();
		String[] reportNameList = reportNames.split(",");
		
		for (String str : reportNameList) {
			logger.info("Loaded report bean " + str);
			beans.add((AbstractReport)context.getBean(str));
		}
		
		return beans;
	}
	
	public static class AppConfig {
		
		@Value("${Tyler.url}")
		String url;
		
		@Value("${Tyler.outputFileLocation}")
		String outputFileLocation;
		
		// TODO Make test database so tests have an empty table to use
		@Autowired
		DataSource datasource;
		
		@Autowired
		RestTemplateBuilder restBuilder;
		
		//////////////
		// Loaders
		////////////
		@Bean
		LoadItems loadItems() {
			LoadItems loader = new LoadItems();
			loader.setItemSummaryDbService(itemSummaryDbService());
			loader.setItemSummaryRestService(itemSummaryRestService());
			return loader;
		}
		@Bean
		LoadPriceData loadPriceData() {
			LoadPriceData loader = new LoadPriceData();
			loader.setItemSummaryDbService(itemSummaryDbService());
			loader.setPriceDataDbService(priceDataDbService());
			loader.setPriceDataRestService(priceDataRestService());
			loader.setPriceDataTimeService(priceDataTimeService());
			return loader;
		}
		@Bean
		LoaderRunner loaderRunner() {
			return new LoaderRunner();
		}
		
		//////////////
		// Reports
		////////////
		@Bean
		MarginFinder marginFinder() {
			MarginFinder report = new MarginFinder();
			report.setItemSummaryDbService(itemSummaryDbService());
			report.setOutputFileLocation(outputFileLocation);
			report.setPriceDataDbService(priceDataDbService());
			report.setPriceDataTimeService(priceDataTimeService());
			return report;
		}
		@Bean
		ReportRunner reportRunner() {
			return new ReportRunner();
		}
		
		////////////////
		// SqlSession
		//////////////
		@Bean
		SqlSessionFactory sqlSessionFactory() {
			Environment environment = new Environment("rs-app", new SpringManagedTransactionFactory(), datasource);
			Configuration configuration = new Configuration(environment);
			configuration.setLazyLoadingEnabled(true);
			
			//Configure ItemSummaryMapper
			configuration.getTypeAliasRegistry().registerAlias(ItemSummary.class.getSimpleName(), ItemSummary.class);
			configuration.addMapper(ItemSummaryMapper.class);
			
			//Configure PriceDataTimeMapper
			configuration.getTypeAliasRegistry().registerAlias(PriceDataTime.class.getSimpleName(), PriceDataTime.class);
			configuration.addMapper(PriceDataTimeMapper.class);
			
			//Configure PriceDataMapper
			configuration.getTypeAliasRegistry().registerAlias(PriceData.class.getSimpleName(), PriceData.class);
			configuration.addMapper(PriceDataMapper.class);
			
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(configuration);
			
			return sqlSessionFactory;
		}
		
		//////////////
		// Mappers
		////////////
		@Bean
		@SuppressWarnings("resource")
		ItemSummaryMapper itemSummaryMapper() {
			SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
			return sessionTemplate.getMapper(ItemSummaryMapper.class);
		}
		@Bean
		@SuppressWarnings("resource")
		PriceDataTimeMapper priceDataTimeMapper() {
			SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
			return sessionTemplate.getMapper(PriceDataTimeMapper.class);
		}
		@Bean
		@SuppressWarnings("resource")
		PriceDataMapper priceDataMapper() {
			SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
			return sessionTemplate.getMapper(PriceDataMapper.class);
		}
		
		
		/////////////////
		// DB Services
		///////////////
		@Bean
		PriceDataTimeService priceDataTimeService() {
			return new PriceDataTimeServiceImpl(priceDataTimeMapper());
		}
		@Bean
		ItemSummaryDbService itemSummaryDbService() {
			return new ItemSummaryDbServiceImpl(itemSummaryMapper());
		}
		@Bean
		PriceDataDbService priceDataDbService() {
			return new PriceDataDbServiceImpl(priceDataMapper());
		}

		

		@Bean
		RestTemplate restTemplate() {
			return restBuilder.build();
		}
		
		///////////////////
		// URL Consumers
		/////////////////
		@Bean
		PriceDataURLConsumer priceDataURLConsumer() {
			return new PriceDataURLConsumer(restTemplate(), url);
		}
		@Bean
		ItemSummaryURLConsumer itemSummaryURLConsumer() {
			
			return new ItemSummaryURLConsumer(restTemplate(), url);
		}
		
		///////////////////
		// Rest Services
		/////////////////
		@Bean
		ItemSummaryRestService itemSummaryRestService() {
			return new ItemSummaryRestServiceImpl(itemSummaryURLConsumer());
		}		
		@Bean
		PriceDataRestService priceDataRestService() {
			return new PriceDataRestServiceImpl(priceDataURLConsumer());
		}
	}

	
}
