package com.nonobank.apps.utils.webintegration;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtils {
	
	/**
	 * 获取某个包名下的所有类的Class
	 * @param packageName 包的路径名
	 * @return 该包下的所有Class
	 */
	public static List<Class<?>> getClasses(String packageName){
		Info ff = null;
        List<Class<?>> classes = new ArrayList<Class<?>>();
        boolean recursive = true;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            while (dirs.hasMoreElements()){  
                URL url = dirs.nextElement();  
                String protocol = url.getProtocol();  
                if ("file".equals(protocol)) {  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);  
                } else if ("jar".equals(protocol)){  
                    JarFile jar;  
                    try {  
                        jar =  ((JarURLConnection) url.openConnection()).getJarFile();  
                        Enumeration<JarEntry> entries = jar.entries();  
                        while (entries.hasMoreElements()) {  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            if (name.charAt(0) == '/') {  
                                name = name.substring(1);  
                            }  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                if (idx != -1) {  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                if ((idx != -1) || recursive){  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            classes.add(Class.forName(packageName + '.' + className));  
                                        } catch (ClassNotFoundException e) {  
                                            e.printStackTrace();  
                                        }  
                                    }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
         
        	return classes;  
    } 
	
	/** 
     * 以文件的形式来获取包下的所有Class 
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes){  
        File dir = new File(packagePath);  
        if (!dir.exists() || !dir.isDirectory()) {  
            return;  
        }  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
              public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
              }  
            });  
        for (File file : dirfiles) {  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),file.getAbsolutePath(),recursive,classes);  
            }  
            else {  
                String className = file.getName().substring(0, file.getName().length() - 6);  
                try {  
                    classes.add(Class.forName(packageName + '.' + className));  
                } catch (ClassNotFoundException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
    
    public static void getClassesDetails(String packageName){
    	List<Class<?>> clazzes = getClasses(packageName);
    	FullPages fullpages = new FullPages();
    	List<Page> pages = new ArrayList<Page>();
    	System.out.println(packageName+"下面的所有字节码："+clazzes);
    	for(Class claz : clazzes){
    		Page singlepage = new Page();
    		List<Methed> metheds = new ArrayList<Methed>();
    		String classpackagename = claz.getPackage().getName()+"."+claz.getName();
    		singlepage.setName(classpackagename);
    		Info infclass = (Info) claz.getAnnotation(Info.class);
    		if(infclass!=null){
    			singlepage.setDesc(infclass.desc());
    			singlepage.setDependence(infclass.dependency());
    		}
    		System.out.println(classpackagename);
    		Annotation[] annotations = claz.getAnnotations();
    		
    		Method[] methods = claz.getDeclaredMethods();
    		for(Method method : methods){
    			List<Param> paramss = new ArrayList<Param>();
    			Info minfo = method.getAnnotation(Info.class);
    			Params mparams = method.getAnnotation(Params.class);
    			Return mreturns = method.getAnnotation(Return.class);
    			Methed siglemethod = new Methed();
    			siglemethod.setName(method.getName());
    			if(minfo!=null){
    				siglemethod.setDesc(minfo.desc());
    				siglemethod.setDependence(minfo.dependency());
    			}
    			String[] paramNames = new String[]{};
    			String[] paramTypes = new String[]{};
    			String[] paramDescs = new String[]{};
    			if(mparams!=null){
    				paramNames = mparams.name();
        		    paramTypes = mparams.type();
        			paramDescs = mparams.desc();
    			}
    			
    			String paramnames = "";
    			String paramtypes = "";
    			String paramdescs = "";
    			for(int i = 0 ; i < paramNames.length ; i++){
    				Param p = new Param();
    				p.setName(paramNames[i]);
    				p.setType(paramTypes[i]);
    				p.setDesc(paramDescs[i]);
    				paramss.add(p);
    			}
    			siglemethod.setParams(paramss);
    			metheds.add(siglemethod);
    		}
    		singlepage.setMethods(metheds);
    		System.out.println("+++++     ++++++++++++"+singlepage);
    		pages.add(singlepage);
    		fullpages.setFullPages(pages);
    		System.out.println("========   ========="+pages.size());
    	}
    	
    }
    
    public static void main(String[] args){
    	getClassesDetails("com.nonobank.apps.business");
	}
	
}  

	
