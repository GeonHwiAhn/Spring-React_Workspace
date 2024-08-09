import React, {useState, useEffect} from 'react';
import '../css/Profile.css';
import axios from 'axios';

const Profile = () => {
    const [files, setFiles] = useState([]);
    const [username, setUserName] = useState('');
    const [profile,setProfile] = useState([]);

//const로 변수명을 설정하거나 기능명 설정
const 파일변경기능 = (e) => {
    //파일을 변경했을 때 프로필 썸네일에 이미지들 주소가 넘어갈 수 있도록 설정
    const 선택한파일들 = Array.from(e.target.files);
    console.log("선택한파일들", 선택한파일들);
    setFiles(선택한파일들);
}

const 유저네임변경기능 = (e) => {
    setUserName(e.target.value);
}



//1. fetch 버전 = 설치가 필요없는 리액트에서 제공하는 java 백엔드와 통신하는 기능
const 이미지업로드1 = () => {
    const formData = new FormData(); //files 이미지 파일이 여러개이기 때문에 묶어서 보내려고
    Array.from(files).forEach(file => {
        formData.append("files", file);
    });
    formData.append("username", username);

    //fetch("백엔드연결주소")
    fetch("http://localhost:9007/profile/upload", {
        method: "POST", //DB에 값을 저장하기 위해 POST 사용
        //headers: { 'content-Type' : 'multipart/form-data'}, //데이터에 파일(이미지)이 포함됨을 자바에 알려줌
        body:formData
    })
    //mysql DB에 값 넣기를 성공했다면! 성공 후 수행할 작업
    .then(response => {
        //응답에 대한 결과를 json형식으로 받음
        return response.json();
    })
    .then(data => {
        // db에 저장된 프로필사진과 닉네임을 보여주기
        // 업로드하고 사용자들이 눈치못채게 새로고침
        게시물가져오기();
    });
}


//2. axios async await 버전 = 3번의 업그레이드 버전 try/catch 를 사용해서 오류 처리
//  async () : 이 기능에는 잠시 대기해야할 코드가 적혀있다.
const 이미지업로드2 = async () => {
    const formData = new FormData(); //files 이미지 파일이 여러개이기 때문에 묶어서 보내려고
    Array.from(files).forEach(file => {
        formData.append("files", file);
    });
    formData.append("username", username);
    // await : formData를 가져오기 전까지 잠시 대기
    await axios.post("http://localhost:9007/profile/upload", formData, {
        //headers: { 'content-Type' : 'multipart/form-data'}, //데이터에 파일(이미지)이 포함됨을 자바에 알려줌
    })
    //mysql DB에 값 넣기를 성공했다면! 성공 후 수행할 작업
    .then(response => {
        //응답에 대한 결과를 json형식으로 받음
        return response.json();
    })
    .then(data => {
        // db에 저장된 프로필사진과 닉네임을 보여주기
        // 업로드하고 사용자들이 눈치못채게 새로고침
        게시물가져오기();
    });
}

//3. axions then 버전
const 이미지업로드3 = () => {
    const formData = new FormData(); //files 이미지 파일이 여러개이기 때문에 묶어서 보내려고
    Array.from(files).forEach(file => {
        formData.append("files", file);
    });
    formData.append("username", username);

    axios.post("http://localhost:9007/profile/upload", formData, {
        //headers: { 'content-Type' : 'multipart/form-data'}, //데이터에 파일(이미지)이 포함됨을 자바에 알려줌
    })
    //mysql DB에 값 넣기를 성공했다면! 성공 후 수행할 작업
    .then(response => {

    /* *****fetch는 이 기능 필요
        return response.json();
    })
    .then(data => {
    axios 알아서 이 기능이 포함(불필요)******/
    const data = response.data;
        게시물가져오기();
    });
}

// 페이지새로고침해서 0.0001초 전에 업로드한 파일 사용자눈에 보여주기
    useEffect(() => {
        게시물가져오기();
    }, []);


    //axios async await
const 게시물가져오기 = async () => {
    const response = await axios.get("http://localhost:9007/profile/watching");
    setProfile(response.data);
    console.log("프로필 가져오기 : " + setProfile);
}
    /* ↑↓ 같음
    const 게시물가져오기 = () => {
    const response = axios.get("http://localhost:9007/profile/watching");
    setProfile(response.data);
    console.log("프로필 가져오기 : " + setProfile);
    }
    */



    return (
        <div>
            <h1>프로필 이미지 업로드</h1>
            {/*
            <input type='file' multiple onChange={(e) => setFiles(e.target.files)} />
                위아래 코드 동일한 기능 작동하지만, const를 이용해서 기능을 구분짓는 것과 기능을 한번에 작성해주는 차이
            <input type='file' multiple onChange={파일변경기능} />
            */}

            <div className='profile-thumbnail'>
                {/*map () => returnXXX
                   map {} => returnOOO */}
                {files.length > 0 && files.map ((file, index) => (
                    <div key={index}>
                        <img src={URL.createObjectURL(file)} />

                    </div>
                ))}
            </div>

            <input type='file' multiple onChange={파일변경기능} /> 
            <input type='text' placeholder='닉네임을 입력하세요.' 
            value={username} onChange={유저네임변경기능} />
            <button onClick={이미지업로드1}>프로필저장하기</button>

            <hr></hr>

            <h3>프로필 상세페이지</h3>
            <div>
                {profile.length > 0 && profile.map((p) => (
                    <div key={p.userId}>
                        <p>{p.username}</p>
                        <p>{p.profileImageUrl}</p>
                        <p>{p.createdAt}</p>
                        {p.profileImageUrl && p.profileImageUrl.split(',').map(image => (
                            <img key={image} src={`http://localhost:9007/images/${image}`} />
                        ))}
                    </div>
                ))}
            </div>
        </div>
    )
}

export default Profile;