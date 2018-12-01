package com.example_2.customBean;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class CustomXmlApplicationContext implements CustomContext
{
    private String fileName="";
    private HashMap<String,Object> map = new HashMap<String,Object>();
    
    public CustomXmlApplicationContext(String fileName) {
        this.fileName = fileName;
        
        if(fileName != null)
        {
            SAXReader reader = new SAXReader();
            
            try {
                //IOC Test construct
                Document doc = reader.read(this.getClass().getClassLoader().getResourceAsStream(fileName));
                
                if(doc == null)
                    return;
                
                //require xpath to get the namespace...?
                Map<String,String> nsMap = new HashMap<String,String>();
                nsMap.put("ns","http://www.springframework.org/schema/beans");
                XPath xsub = doc.createXPath("//ns:beans/ns:bean");
                xsub.setNamespaceURIs(nsMap);
                
                List<Node> nodeList = xsub.selectNodes(doc);
                
                if(nodeList != null && nodeList.size() > 0)
                {
                    for(Node node : nodeList)
                    {
                        Element element = (Element)node;
                        
                        String idName = element.attributeValue("id");
                        String className = element.attributeValue("class");
                        
                        Object o = Class.forName(className).newInstance();
                        
                        System.out.println(idName + "   :   "  + className + "      :   " + o);
                        
                        map.put(idName, o);
                    }
                }
                
                //DI
                xsub = doc.createXPath("//ns:beans/ns:bean/ns:property");
                xsub.setNamespaceURIs(nsMap);
                
                nodeList = xsub.selectNodes(doc);
                
                if(nodeList != null && nodeList.size() > 0)
                {
                    for(Node node : nodeList)
                    {
                        Element element = (Element)node;
                        Element parent = element.getParent();
                        
                        String parentId = parent.attributeValue("id");
                        String refId = element.attributeValue("ref");
                        
                        Object refObject = map.get(refId);
                        Object parentObj = map.get(parentId);
                        
                        System.out.println("Parent Obj :" + parentObj + "   Ref Object : " + refObject);
                        
                        PropertyDescriptor[] propertyDescriptor = Introspector.getBeanInfo(parentObj.getClass()).getPropertyDescriptors();
                        
                        for(PropertyDescriptor pd : propertyDescriptor)
                        {
                            if(pd.getName().equals(refId.toLowerCase()))
                            {
                                pd.getWriteMethod().invoke(parentObj, refObject.getClass().newInstance());  //is a new instance or?...
                            }
                        }
                        
                        System.out.println(parentObj);
                        
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public Object getBean(String beanName)
    {
        return map.get(beanName);
    }

}
