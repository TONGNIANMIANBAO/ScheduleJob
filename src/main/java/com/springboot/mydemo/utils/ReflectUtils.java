package com.springboot.mydemo.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReflectUtils {

	private static String urlPath;

	@Value("${jar.url.path}")
	public void setUrlPath(String path) {
		urlPath = path;
	}

	public static Runnable getInstance(String clazz, String param)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		return getInstance("", clazz, param);
	}

	public static Runnable getInstance(String path, String clazz, String param)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		String defaultPath = urlPath;
		if (!StringUtils.isBlank(path)) {
			defaultPath = path;
		}

		File file = new File(defaultPath);
		URL url = file.toURI().toURL();// 获得jar地址
		URLClassLoader loader = new URLClassLoader(new URL[] { url });// 获得类加载器
		try {
			Class<?> cls = loader.loadClass(clazz);// 加载外部类
			Constructor<?> constructor = cls.getConstructor(String.class);
			return (Runnable) constructor.newInstance(param);
		} finally {
			loader.close();
		}
	}

}
