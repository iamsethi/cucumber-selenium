package com.amazon.interfaces;

import com.amazon.driver.DriverManager;
import com.google.inject.Provider;

public interface IDriverProvider {

	public Provider<DriverManager> get(String driver);

}