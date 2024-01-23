package ex04;

public class DogApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = "ex04.Dog";

        Class cls = Class.forName(className); // 어떤 클래스를 만들지 모를 때 이렇게 new 를 함. 클래스 이름을 몰라도 됨.
        Object ob = cls.newInstance();

        Dog d = (Dog) ob ; // 오브젝트 타입이기 때문에 다운캐스팅
        System.out.println(d.name);
    }
}
