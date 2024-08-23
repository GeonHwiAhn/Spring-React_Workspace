import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import ChickenList from './component/ChickenList';
import ChickenForm from './component/ChickenForm';
import MainRouter from './MainRouter';
import ChickenDetail from './component/ChickenDetail';
import SearchResult from './component/SearchResult';

/*
ChickenList paht="/"

ChickenDetail.js path="chicken-detail"
*/

function App () {
    return (
    <Router>
        <Routes>
            <Route path="/" element={<MainRouter />}/>
            {/*    Routes 안에는 Route로 설정된 태그만 들어올 수 있음    <MainRouter />*/}
            <Route path="/chicken-detail/:id" element={<ChickenDetail />}/>
            <Route path='/search' element={<SearchResult />} />
        </Routes>
    </Router>
    )
}

export default App;