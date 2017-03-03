/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bat15.restful.process;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 *
 * @author Павел
 */
@Stateless
@LocalBean
public class ThingPropertyProcessor {
    
    

    private final Object lock = new Object();
    
    @PostConstruct
    @Schedule(second="0", minute="0", hour="*/6")
    public void init() {
        synchronized (lock) {           
            System.out.println("########## WebTechCCBO Bean created.  Creation time: "+new Date());
            // проверяем пропертисы, которые сохранены в glassfish'е
            //AppProperties.checkProperties(properties);
            
            try{
                //jdbcConnection = JDBCConnection.connect();   
            }catch(Exception ex){
                System.out.println("jdbcConnection connect try");
                ex.printStackTrace();
            }            
            
//            try{
//                hbaseConnection = HbaseConnection.connect(properties);   
//            }catch(Exception ex){ex.printStackTrace();}
        }
    } 
    
    
    @PreDestroy
    public void shutdown()
    {
        try{
            //jdbcConnection.disconnect();
        }catch(Exception ex){
            System.out.println("jdbcConnection disconnect try");
            ex.printStackTrace();
        }        
        
//        try{
//            hbaseConnection.disconnect();
//        }catch(Exception ex){ex.printStackTrace();}
//        
//        System.out.println("########## WebTechCcbo Bean shutted down.  Shutdown at : "+new Date());
    }    
    
    public void putPropertyValue(String data) {
        
        
    }
    
    public String getPropertyValue(int type){
        
        String data = "";
        
        if(type == 0) data = "{test_json: {name: value}}";
        else if(type ==1) data = "{name: value}";
                
                
        return data;
    }
    
    public String getModel(String modelQuery){
        
        if(modelQuery == null) return "{error: {model: null}}";
        
        ArrayList<String> paths = new ArrayList();
        
        for(String path:modelQuery.split("/"))
        {
            paths.add(path);
        }
        
        switch (paths.size()) {
            case 0:
                System.out.println("0 size: " + paths.size());
                break;
            case 1:
                System.out.println("1 size: " + paths.size());
                break;
                
            case 2:
                System.out.println("2 size: " + paths.size());
                break;
            default:
                System.out.println("3 size: " + paths.size());
                break;
        }
            
        String response;
        
                
        return modelQuery;
    }    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}