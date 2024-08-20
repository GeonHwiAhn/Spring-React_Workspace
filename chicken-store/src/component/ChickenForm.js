import React, {useState, useEffect} from "react";
import axios from 'axios';

const ChickenForm = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');

    //스프링부트 연결 후 전달
    const submitButton = () => {
        axios.post("http://localhost:9090/api/chicken", 전달데이터)
    }

    return (
        
        <div className="chickenform-container">
            <label >
                메뉴 이름 : 
                <input type="text" />
            </label>
            <label>
                메뉴 설명 : 
                <textarea />
            </label>
            <label>
                가격 : 
                <input type="number" />
            </label>
            <button>등록하기</button>
            <button>메인으로 돌아가기</button>
        </div>
    )
}

export default ChickenForm;