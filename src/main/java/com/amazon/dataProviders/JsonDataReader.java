package com.amazon.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import com.amazon.pages.BasePage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonDataReader {
	private Logger log = Logger.getLogger(this.getClass());

	private static String dataFile = System.getProperty("user.dir") + File.separator + "src/test/resources/sfa.json";
	static Gson gson = new Gson();
	private static JsonObject jsonObject;
	private static JsonObject commondata;
	private static JsonObject environments;

	public static void registerEnvironment(String envn) {
		try (FileReader reader = new FileReader(dataFile)) {
			jsonObject = gson.fromJson(new FileReader(dataFile), JsonObject.class);
			environments = jsonObject.getAsJsonObject("Environments");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void initializeJSON() {
		commondata = jsonObject.getAsJsonObject("commondata");
	}

	public void fillAllFields(BasePage id) {
		JsonElement subContainers = getAllSubContainers(id.getClass().getSimpleName());
		Set<Map.Entry<String, JsonElement>> s = subContainers.getAsJsonObject().entrySet();
		Iterator<Map.Entry<String, JsonElement>> i = s.iterator();
		while (i.hasNext()) {
			Map.Entry<String, JsonElement> m = i.next();
			fillCustomFields(id, m.getKey());
		}

	}

	public void fillCustomFields(BasePage id, String dataTarget) {
		JsonElement page = commondata.get(id.getClass().getSimpleName());
		JsonElement dataContainer = getDataContainer(page, dataTarget);
		fillFields(id, page, dataContainer);
	}

	public static JsonElement getDataContainer(JsonElement page, String dataTarget) {
		JsonObject dataContainer = page.getAsJsonObject();
		return dataContainer.get(dataTarget);
	}

	public void fillFields(BasePage id, JsonElement page, JsonElement dataContainer) {
		LinkedHashMap<String, String> containerFields = getContainerFields(dataContainer);
		for (Map.Entry<String, String> entry : containerFields.entrySet()) {
			String locator = entry.getKey();
			String value = entry.getValue();
			try {
				Class<?> aClass = id.getClass();
				Field field = aClass.getField(locator);
				WebElement element = ((WrapsElement) field.get(id)).getWrappedElement();
				if (locator.startsWith("tbx_")) {
					 log.info("Filling " + locator + " with value : " + value + " ");
					element.clear();
					element.sendKeys(value);
				} else if (locator.startsWith("rbn_")) {
					log.info("Filling " + locator + " with value : " + value + " ");
				} else if (locator.startsWith("ddl_")) {
					log.info("Filling " + locator + " with value : " + value + " ");
				} else if (locator.startsWith("btn_")) {
					log.info("Filling " + locator + " with value : " + value + " ");
					element.click();
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	public static LinkedHashMap<String, String> getContainerFields(JsonElement dataContainer) {
		LinkedHashMap<String, String> fields = new LinkedHashMap<String, String>();
		JsonArray i = dataContainer.getAsJsonArray();
		for (JsonElement field : i) {
			String locator = field.getAsString().split(": ")[0]; // tbx_name
			String value = field.getAsString().split(": ")[1]; // Brian
			fields.put(locator, value);
		}
		return fields;
	}

	public static JsonElement getAllSubContainers(String pageName) {
		JsonElement page = commondata.get(pageName);
		JsonObject subContainers = page.getAsJsonObject();
		return subContainers;
	}

}