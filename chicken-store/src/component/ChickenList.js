import React, {useState, useEffect} from "react";
import axios from 'axios';

const ChickenList = () => {
    const [chickens, setChickens] = useState([]);

//최초 1회 실행하게 하는 useEffect 이용해서 처음에 치킨리스트.js 시작하자마자 DB에 저장된 치킨메뉴들 가져오기
// useEffect(() => {기능설정}, [언제 다시 기능을 동작시킬것인가, 빈칸일경우 한번동작하고 동작x])
    useEffect(() => {
        axios.get("http://localhost:9090/api/chicken")
        .then(response => 
            setChickens(response.data)) //가져온 데이터를 chickens 변수에 저장하는 과정
        .catch(err =>
            alert("불러오는데 문제가 발생했습니다." + err)
        );
    }, [])

/*
    const deleteChicken = () => {
        axios.delete("http://localhost:9090/api/chicken")
        .then(() => {
            setChickens
        })
    }
*/

    return (
        <div className="chicken-container">
            <h1>치킨 메뉴</h1>
            <ul>
            {chickens.map(chicken => (
                <li key={chicken.id}>
                    {chicken.chickenName} = {chicken.description} = ₩{chicken.price}원
                    <button /*onClick={deleteChicken}*/>메뉴 삭제하기</button>
                </li>
            ))}
            </ul>
        </div>
    )
}

export default ChickenList;