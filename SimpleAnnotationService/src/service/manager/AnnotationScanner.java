package service.manager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AnnotationScanner {

	/**
	 * Scans all classes accessible from the context class loader which belong
	 * to the given package and subpackages.
	 * 
	 * @param packageName
	 *            The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Method> getMethodsForAnnotation(String packageToScan, Class annotation) {
		List<Method> methods = new ArrayList<>();
		for (Class c : getClasses(packageToScan))
			for (Method m : c.getMethods())
				if (m.isAnnotationPresent(annotation))
					methods.add(m);
				
		return methods;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getClassesForAnnotation(String packageToScan, Class annotation) {
		List<Class> classes = new ArrayList<>();
		for (Class c : getClasses(packageToScan))
			if (c.isAnnotationPresent(annotation))
				classes.add(c);

		return classes;
	}

	@SuppressWarnings("rawtypes")
	public static List<Class> getClasses(String packageName) {
		List<Class> classes = new ArrayList<Class>();
		try{
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String path = packageName.replace('.', '/');
			Enumeration<URL> resources = classLoader.getResources(path);
			List<File> dirs = new ArrayList<File>();
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				dirs.add(new File(resource.getFile()));
			}
			
			for(File directory : dirs)
				classes.addAll(findClasses(directory, packageName));

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and
	 * subdirs.
	 * 
	 * @param directory
	 *            The base directory
	 * @param packageName
	 *            The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */

	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists())
			return classes;

		File[] files = directory.listFiles();
		for (File file : files)
			if (file.isDirectory())
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			else if (file.getName().endsWith(".class"))
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));

		return classes;
	}
}
