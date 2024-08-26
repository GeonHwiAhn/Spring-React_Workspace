import {BrowserRouter as Router, Route, Routes }from 'react-router-dom';
import './App.css';
import PizzaList from './components/PizzaList';
import PizzaForm from './components/Pizzaform';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<PizzaList />} />
        <Route path='/' element={<PizzaForm />} />
      </Routes>
    </Router>
  );
}

export default App;
