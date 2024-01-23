package ex01;

public class UserController {

    // /login
    public void login(){
        System.out.println("로그인");
    }
    // /join
    public void join(){
        System.out.println("회원가입 호출됨");
    }
    public void userinfo(){
        System.out.println("유저정보 공개"); // 이 정보는 절대 공개가 안됨. 전화를 해야됨
    }

}
