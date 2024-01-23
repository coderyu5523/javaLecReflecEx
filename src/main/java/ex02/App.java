package ex02;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        String path = "/updatepassword";

        UserController con = new UserController();

        Method[] methods = con.getClass().getDeclaredMethods(); //getDeclaredMethods 는 UserController 의 메서드를 다 들고옴
//        System.out.println(methods.length);  // Method[] 의 크기
        for (Method method : methods) {
//            System.out.println(method.getName());  메서드 이름
            RequestMapping rm = method.getDeclaredAnnotation(RequestMapping.class);// 이 메서드에 어떤 어노테이션이 있는지
            if(rm==null)continue; // user 컨트롤러에서 어노테이션이 없는 것 때문에 오류남. 그래서 널 값으면 무시하고 아래 코드 실행
            if(rm.uri().equals(path)){  //외부에서 들어온 path 와 uri 가 같다면 메서드 호출
                try {
                    method.invoke(con); // = con.login() 메서드를 리플렉션으로 호출 , con 은 힙이 여러개 일 수 있기 때문에 어떤 힙의 메서드인지 구분하기 위해 넣음.
                    break; //찾으면 끝
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
