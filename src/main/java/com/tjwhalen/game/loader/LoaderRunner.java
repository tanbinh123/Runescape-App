package com.tjwhalen.game.loader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoaderRunner {

	private final static Logger logger = LoggerFactory.getLogger(LoaderRunner.class);
	private List<AbstractLoader> loaders;
	
	public void setLoaders(List<AbstractLoader> loaders) {
		this.loaders = loaders;
	}
	
	public void execute() {
		
		if (loaders == null) {
			logger.warn("No loaders specified");
		} else {
			for (AbstractLoader loader : loaders) {
				loader.loadData();
			}
			
		}
	}
	
	
}
