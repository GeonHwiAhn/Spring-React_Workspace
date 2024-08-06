import React, { useState } from 'react';
import axios from 'axios';

function App() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [files, setFiles] = useState([]);

    const Java에업로드 = () => {
      // Form 특정값을 가져와서 넘겨줄 때 사용하는 객체
      // files에서 파일이 하나가 아니라 여러개이기 때문에 여러개를 담을 배열 설정
      const formData = new FormData();
      Array.from(files).forEach(file => {
        formData.append("files", file);
      });
      formData.append("title", title);
      formData.append("content", content);


      //자바 컨트롤러에 데이터 전송! Post
      axios.post("http://localhost:9007/gallery/upload", formData, {
        headers: {
          //전송할 데이터에 글자가 아닌 파일이 함께 전송된다 머릿말로 알려주기
          'Content-Type' : 'multipart/form-data'
        }
      });
      alert("자바로 이미지 전송했습니다.")
    }



    const imgSrc = `${process.env.PUBLIC_URL}/images/${post.image_url}`;


    return (
        <div className="App">
            
                <div>
                    <label>제목:</label>
                    <input type='text' value={title} onChange={(e) => setTitle(e.target.value)}/>
                </div>
                <div>
                    <label>내용:</label>
                    <textarea value={content} onChange={(e) => setContent(e.target.value)} />
                </div>
                <div>
                    <label htmlFor='이미지선택'>이미지선택</label>
                    <input multiple type="file" id='이미지선택' className='img-input' 
                    onChange={(e) => setFiles(e.target.files)}
                    />
                </div>
                <button onClick={Java에업로드}>Submit</button>
            

            {/*}
                <div>
                    {posts.map(post => (
                      <div key={post.id}  >
                      <h2> {post.title}</h2>
                      <p>  {post.content}</p>
                         
                      <img src={imgSrc} alt={post.image_url} />
                    
                      </div>
                ))}
                </div>
            */}
        </div>
    );
}

export default App;