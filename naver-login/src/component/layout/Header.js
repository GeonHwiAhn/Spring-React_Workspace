import React, {useContext} from "react";
import AuthContext from "./AuthContext";
import {Link} from "react-router-dom";

const Header = () => {
    const {loginMember, setLoginMember} = useContext(AuthContext);
    /*
    [] -> 변수를 새로 설정
    const [loginMember, setLoginMember] = useContext(AuthContext);
    {} -> 외부에서 작성된 변수를 가져와서 사용할 때 설정
    const {loginMember, setLoginMember} = useContext(AuthContext);
    */
    return(
        <header>
        <h1>헤더부분</h1>
        <nav>
            {/*loginMember가 ? (존재한다면) : (존재하지않는다면) */}
            {/*
                                            java dto.NaverUser에 저장된 변수명 중 이름을 갖고오고 싶다면 name
                                        왜냐하면 NaverUser에 Private String name; 적어놨기 때문
            loginMember     ? (<span>환영합니다.{loginMember.dto에저장된변수명}님</span>) : () 
            */}

                
            {loginMember    ? (
                //태그 2개 이상 사용시 div로 묶어줘야함!!!
                <div>
                    <span>환영합니다.{loginMember.name}님</span>
                    <button>로그아웃</button>
                </div>
                ) : (
                    <div>
                    <Link to="/login">로그인하기</Link>
                    <Link to="/회원가입api url">회원가입하기</Link>
                    </div>
                ) }
        </nav>
        </header>
    )
}
export default Header;