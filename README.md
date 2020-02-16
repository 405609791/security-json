# security-json
json格式登录，正常时无法登录的。

* UsernamePasswordAuthenticationFilter类中attemptAuthentication方法

* 知识点
举例讲解Java的Jackson库中ObjectMapper类的使用,Jackson库通常被用来实现Java的对象和JSON之间的转换功能
ObjectMapper类是Jackson库的主要类。它提供一些功能将转换成Java对象匹配JSON结构，反之亦然。
它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写
这个类继承了以下类方法：
java.lang.Object
例子
测试类基本代码如下
/* 
 * @project java 
 * @package 
 * @file Jackson.java 
 * @version 1.0 
 
 */ 
  
public class Jackson { 
  /* 
   * 
   * Class Descripton goes here. 
   * 
   * @class Jackson 
   * @version 1.0 
   */ 
  public static JsonGenerator jsonGenerator = null; 
  private static ObjectMapper mapper = new ObjectMapper(); 
  public static void main(String[] args) { 
    Student student = new Student(); 
    student.setIsstudent(true); 
    student.setUid(1000); 
    student.setUname("xiao liao"); 
    student.setUpwd("123"); 
    student.setNumber(12); 
      
    Map<String, Student> stuMap = new HashMap<String, Student>(); 
    stuMap.put("1", student); 
    stuMap.put("2", student); 
      
    List<Object> stuList = new ArrayList<Object>(); 
    List<Student> stuList1 = new ArrayList<Student>(); 
    stuList1.add(student); 
    student= new Student(); 
    student.setIsstudent(false); 
    student.setUid(200); 
    student.setUname("xiao mi"); 
    stuList1.add(student); 
      
    stuList.add(student); 
    stuList.add("xiao xin"); 
    stuList.add("xiao er"); 
    stuList.add(stuMap); 
      
    //readJson2List(); 
    try { 
      //readJson2Array(); 
      //writeArray2Json(array); 
      //writeJson2List(); 
      //writeEntity2Json(student); 
      writeJson2Entity(); 
      //writeMap2Json(stuMap); 
      //writeList2Json(stuList1); 
        
    } catch (IOException e) { 
      e.printStackTrace(); 
    } 
  } 
   /** 
   * 
   * <code>writeEntity2Json</code> 
   * @description: TODO(实体类转换成json) 
   * @param object 
   * @throws IOException 
   */ 
   public static void writeEntity2Json(Object object) throws IOException { 
      mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"),object ); 
      mapper.writeValue( System.out,object ); 
       
   } 
   /** 
   * 
   * <code>writeArray2Json</code> 
   * @description: TODO(数组转换成json数组) 
   * @param object 
   * @throws IOException 
   */ 
   public static void writeArray2Json(Object object) throws IOException { 
       
     // writeValue具有和writeObject相同的功能 
     mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"),object ); 
     mapper.writeValue(System.out,object ); 
       
   } 
   /** 
   * 
   * <code>writeMap2Json</code> 
   * @description: TODO(map对象转换成json对象) 
   * @param object 
   * @throws IOException 
   * @since  2011-11-8   廖益平 
   */ 
   public static void writeMap2Json(Object object) throws IOException { 
       
     System.out.println("使用ObjectMapper-----------"); 
     // writeValue具有和writeObject相同的功能 
     System.out.println("==>"+mapper.writeValueAsString(object)); 
     mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aamap.txt"),object ); 
     mapper.writeValue( System.out , object ); 
   } 
   /** 
   * 
   * <code>writeList2Json</code> 
   * @description: TODO(list转换成json) 
   * @param object 
   * @throws IOException 
   */ 
   public static void writeList2Json(Object object) throws IOException { 
     System.out.println("==>"+mapper.writeValueAsString(object)); 
     mapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aamap.txt"),object ); 
     mapper.writeValue( System.out , object ); 
   } 
   /** 
   * 
   * <code>writeJson2Entity</code> 
   * @description: TODO(json转换成实体) 
   * @throws IOException 
   */ 
   public static void writeJson2Entity() throws IOException { 
     System.out.println("json串转换成entity-------------"); 
//    File file = new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"); 
//    FileInputStream inputStream = new FileInputStream(file); 
//    Student student = mapper.readValue(inputStream,Student.class); 
//    System.out.println(student.toString()); 
     //漂亮输出 
     //mapper.defaultPrettyPrintingWriter().writeValueAsString(value); 
    
     String json = "{\"uid\":1000,\"uname\":\"xiao liao\",\"upwd\":\"123\",\"number\":12.0,\"isstudent\":true}"; 
     Student student1 = mapper.readValue(json,Student.class); 
     System.out.println("json2:"+student1.toString()); 
   } 
   /** 
   * 
   * <code>writeJson2List</code> 
   * @description: TODO(json专程list对象) 
   * @throws IOException 
   */ 
   public static void writeJson2List() throws IOException { 
     System.out.println("json串转换成entity-------------"); 
     File file = new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"); 
     FileInputStream inputStream = new FileInputStream(file); 
     Student student = mapper.readValue(inputStream,Student.class); 
     System.out.println(student.toString()); 
       
     String json = "[{\"uid\":1000,\"uname\":\"xiao liao\",\"upwd\":\"123\",\"number\":12.0,\"isstudent\":true},{\"uid\":200,\"uname\":\"xiao mi\",\"upwd\":null,\"number\":0.0,\"isstudent\":false}]"; 
     List<LinkedHashMap<String, Object>> s= mapper.readValue(json,List.class); 
     for (int i = 0; i < s.size(); i++) { 
       LinkedHashMap<String, Object> link = s.get(i); 
       Set<String> key = link.keySet(); 
       for (Iterator iterator = key.iterator(); iterator.hasNext();) { 
        String string = (String) iterator.next(); 
        System.out.println(string+"==>"+link.get(string)); 
          
      } 
       System.out.println("json:"+i+""+s.get(i).toString()); 
        
    } 
   } 
   /** 
    * JSON转换为List对象 
    */ 
   public static void readJson2List() { 
    String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"}," 
     + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]"; 
    try { 
    List<LinkedHashMap<String, Object>> list = mapper.readValue( 
     json, List.class); 
    System.out.println(list.size()); 
    for (int i = 0; i < list.size(); i++) { 
     Map<String, Object> map = list.get(i); 
     Set<String> set = map.keySet(); 
     for (Iterator<String> it = set.iterator(); it.hasNext();) { 
     String key = it.next(); 
     System.out.println(key + ":" + map.get(key)); 
     } 
    } 
    } catch (JsonParseException e) { 
    e.printStackTrace(); 
    } catch (JsonMappingException e) { 
    e.printStackTrace(); 
    } catch (IOException e) { 
    e.printStackTrace(); 
    } 
   } 
   /** 
    * JSON转换为List对象 
    */
   public static void readJson2Array() { 
     String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
       + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]"; 
     try { 
       Student[] students = mapper.readValue(json, Student[].class); 
       for (Student student : students) { 
        System.out.println(">"+student.toString()); 
      } 
     } catch (JsonParseException e) { 
       e.printStackTrace(); 
     } catch (JsonMappingException e) { 
       e.printStackTrace(); 
     } catch (IOException e) { 
       e.printStackTrace(); 
     } 
   } 
  
} 

打印结果 ：
串转换成entity-------------
json2:uid=1000,name=xiao liao,upwd=123,number=12.0,isStudent=true
 
writeMap2Json -----------
{"2":{"uid":1000,"uname":"xiao liao","upwd":"123","number":12.0,"isstudent":true},"1":{"uid":1000,"uname":"xiao liao","upwd":"123","number":12.0,"isstudent":true}}
 
readJson2Array------------------
>uid=1,name=www,upwd=456,number=234.0,isStudent=false
>uid=5,name=tom,upwd=123,number=3.44,isStudent=false
writeMap2Json -----------
{"2":{"uid":1000,"uname":"xiao liao","upwd":"123","number":12.0,"isstudent":true},"1":{"uid":1000,"uname":"xiao liao","upwd":"123","number":12.0,"isstudent":true}}

