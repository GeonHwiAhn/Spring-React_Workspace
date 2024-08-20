import './App.css';
import ChickenForm from './component/ChickenForm';
import ChickenList from './component/ChickenList';
import Modal from './component/Modal';
import {useState} from 'react';

const App= () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  //사용자가 보고싶을 때 볼 수 있도록 처음에는 false 보이지않음 설정해줌

  // 오픈 true 닫음 false
  // const에서 동작하는 기능이 1개일 때 {} 생략 가능
  const openModal = () => setIsModalOpen(true);
  
  const closeModal = () => {
    setIsModalOpen(false);
  }


  return (
    <div className="App">
      <ChickenList />

      <button onClick={openModal}>메뉴등록하기</button>
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm />
      </Modal>
    </div>
  );
}

export default App;
