package ex03;

@Controller  // 패키지에 깃발을 꽂음
public class UserController {

    // /login
    @RequestMapping(uri="/login")
    public void login(){
        System.out.println("로그인");
    }
    // /join
    @RequestMapping(uri="/join")
    public void join(){
        System.out.println("회원가입 호출됨");
    }

    @RequestMapping(uri="/userinfo")
    public void userinfo(){
        System.out.println("유저정보 공개");} // 이 정보는 절대 공개가 안됨. 전화를 해야됨

    @RequestMapping(uri="/updatepassword")
    public void updatePassword(){
        System.out.println("패스워드 수정하기");
    }

}
