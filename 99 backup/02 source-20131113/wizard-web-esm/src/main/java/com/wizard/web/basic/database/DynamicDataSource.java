package com.wizard.web.basic.database;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wizard.web.utils.SpringContextUtils;

public class DynamicDataSource extends AbstractRoutingDataSource {

	private String dataSource = null;

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public static void swtichDataSource(String dataSource) {
		DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextUtils
				.getBean("dataSource");
		dynamicDataSource.setDataSource(dataSource);
	}

	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getTargetDataSources() {

		try {
			Field field = AbstractRoutingDataSource.class
					.getDeclaredField("targetDataSources");
			field.setAccessible(true);
			return (Map<Object, Object>) field.get(SpringContextUtils
					.getBean("dataSource"));

		} catch (NoSuchFieldException | SecurityException
				| IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSource;
	}

}
