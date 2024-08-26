import {BrowserRouter as Router, Route, Routes }from 'react-router-dom';
import './App.css';
import PizzaList from './components/PizzaList';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<PizzaList />} />
      </Routes>
    </Router>
  );
}

export default App;
