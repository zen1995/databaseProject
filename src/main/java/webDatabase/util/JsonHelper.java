package webDatabase.util;


import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonHelper {

    public static String jsonEncode(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        //mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) ;
        //mapper.configure( SerializationConfig.Feature.AUTO_DETECT_GETTERS,false);

//        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        mapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);
//        mapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);
//        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String s = null;
        JsonGenerator jsonGenerator = null;
        try{
            s = mapper.writeValueAsString(obj);
//            s = s.replaceAll("\\\"","\"");
//            System.out.println(s);
        }
        catch (Exception e){
            s = "";
            String errMessage = "can not get json encode : "+e.getMessage();
            System.out.println(errMessage);
        }
        return s;
    }
//    public static String jsonEncode(HashMap map){
//        ObjectMapper mapper = new ObjectMapper();
//        String s = null;
//        JsonGenerator jsonGenerator = null;
//        try{
//            s = mapper.writeValueAsString(map);
//        }
//        catch (Exception e){
//            s = "";
//            String errMessage = "can not get json encode : "+e.getMessage();
//            System.out.println(errMessage);
//            Logger logger = MyLogger.getLogger();
//            logger.log(Level.ALL,errMessage);
//        }
//        return s;
//    }

//    public static String jsonEncode(Object map){
//        ObjectMapper mapper = new ObjectMapper();
//        String s = null;
//        JsonGenerator jsonGenerator = null;
//        try{
//            s = mapper.writeValueAsString(map);
//        }
//        catch (Exception e){
//            s = "";
//            String errMessage = "can not get json encode : "+e.getMessage();
//            System.out.println(errMessage);
//            Logger logger = MyLogger.getLogger();
//            logger.log(Level.ALL,errMessage);
//        }
//        return s;
//    }
}
