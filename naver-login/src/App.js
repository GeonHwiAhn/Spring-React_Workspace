import './App.css';
import Login from './component/Login';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import UserInfo from './component/UserInfo';
import Header from './component/layout/Header';
import {useEffect, useState} from "react";
import AuthContext from './component/layout/AuthContext';
// html파일이 1개 밖에 없는 React에서는 
// Router를 이용해서 각 js파일의 경로를 설정
// BrowserRouter = Router : 웹에 전체적인 경로음
// Switch   -> Routes     : 경로들
// Route                  : 경로
function App() {
  //로그인 정보를 받고 전달
  const [loginMember, setLoginMember] = useState(null);

  // 만약에 로그인한 정보가 있다면 localStorage 저장
  useEffect(() => {
    if (loginMember) {
      localStorage.setItem("loginMember", JSON.stringify(loginMember));
    }
   //[loginMember] = 로그인한 멤버가 변경될 때마다 새로고침
  }, [loginMember]);

  //로그인이 저장된 정보를 전달
  useEffect(() => {
    const savedMember = localStorage.getItem("loginMember");
    // 만약에 loginMember 변수에 저장된 회원정보가 존재한다면 setLoginMember에 넣어주겠다.
    if (savedMember) {
      setLoginMember(JSON.parse(savedMember)); //데이터 손실 방지르 위해 JSON 형식으로 parse(변환)
    }
  }, []);

  return (
    <AuthContext.Provider value={{loginMember, setLoginMember}}>
    <Router>
      <Header/>
        <Routes>
          <Route path='/login' element={<Login     />  } />
          <Route path='/userinfo' element= { <UserInfo />} />
        </Routes>

    </Router>
    </AuthContext.Provider>
  );
}

export default App;