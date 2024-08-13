import '../css/Login.css';


const Login = () => {
  return (
    <div className='login-container'>
      <h3>로그인하기</h3>
      <div>
        <label>
          아이디 :
          <input type="text" placeholder="아이디를 입력하세요." />
        </label>
        <label>
          비밀번호 :
          <input type="password" placeholder="비밀번호를 입력하세요." />
        </label>
        <button>로그인하기</button>
        <div className='find-sign-buttons'>
          <button>아이디 찾기</button>
          <button>비밀번호 찾기</button>
          <button>회원가입 하기</button>
        </div>
      </div>
      <label>
          sns로 로그인하기 : 
      <img src="./naver_img/btnG_icon_round.png" className='naver-logo-img'/>
      </label>
      {/*
      <button className='naver-login-button'>
        <img src="./naver_img/btnG_icon_round.png" />
        네이버로 회원가입하기
      </button>
       */}
    </div>
  );
};

export default Login;
