package ex03;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class App {

    public static void findUri(List<Object> instances,String path){

        for(Object instance : instances){
            Method[] methods = instance.getClass().getDeclaredMethods(); //getDeclaredMethods 는 UserController 의 메서드를 다 들고옴
            for (Method method : methods) {
                RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);// 이 메서드에 어떤 어노테이션이 있는지
                if(rm==null)continue; // user 컨트롤러에서 어노테이션이 없는 것 때문에 오류남. 그래서 널 값으면 무시하고 아래 코드 실행
                if(rm.uri().equals(path)){  //외부에서 들어온 path 와 uri 가 같다면 메서드 호출
                    try {
                        method.invoke(instance); // = con.login() 메서드를 리플렉션으로 호출 , con 은 힙이 여러개 일 수 있기 때문에 어떤 힙의 메서드인지 구분하기 위해 넣음.
                        break; //찾으면 끝
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }




    public static void main(String[] args) throws URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //패키지를 분석해서 @컨트롤러  띄울 것을 new 해서 셋에 담음, 싱글톤으로 담김,셋 자료형은 중복 안됨
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); // 패키지를 분석할 수 있음.
        URL packageUrl = classLoader.getResource("ex03"); //ex03 폴더의 어노테이션을 분석함, classLoader 은 메인의 자바에 접근해서 패키지를 분석함.

        File ex03 = new File(packageUrl.toURI());

//        Set<Object> instances = new HashSet(); //
        List<Object> instances = new ArrayList<>();

        for(File file : ex03.listFiles()){  // ex03 패키지 파일이 4개 있어서 4번 반복함.
            System.out.println(file.getName());
            if(file.getName().endsWith(".class")){  // 이름의 끝이 .class 면 실행
                String className = "ex03"+"."+file.getName().replace(".class",""); // .class 를 공백으로 처리 .CLASS가 있으면 NEW 를 못함
//                System.out.println(className);

                Class cls = Class.forName(className);
                if (cls.isAnnotationPresent(Controller.class)){
                    Object instance = cls.newInstance();
                    instances.add(instance); // usercontroller , BoardController 스캔함, 어노테이션이 붙어있기 때문에


                }
            }


        }

        // 패키지를 리플렉션 해서 모든 컨트롤러를 찾음.
        findUri(instances,"/login");
//        UserController con = new UserController();



    }
}
