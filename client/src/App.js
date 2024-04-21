import { useEffect, useRef, useState } from 'react';
import { IconDeviceMobile, IconBrandApple, IconBrandAndroid, IconCalendarWeek, IconCpu, IconCamera } from '@tabler/icons-react';
import './App.css';

function App() {
  const [smartphoneList, setSmartphoneList] = useState(null);
  const [smartphoneInputs, setSmartphoneInputs] = useState({
    name: "",
    brand: "",
    year: "",
    systemName: "",
    screenSize: "",
    cpu: "",
    cameraNumber: "",
    cameraMP: "",
    imageLink: ""
  })
  const addModalRef = useRef(null);

  async function getData() {
    try {
      const response = await fetch("http://localhost:8080/smartphones/get");
      const data = await response.json();
      setSmartphoneList(data);
    } catch (error) {
      console.error(error);
    }
  }

  function showModal(){
    addModalRef.current.showModal();
  }

  function handleInputChange(event, key){
    const value = event.target.value;
    setSmartphoneInputs(prev => ({
      ...prev,
      [key] : value
    }))
  }

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className="container">
      <h1>CellMax</h1>
      <section className="smartphoneListBox">
      {smartphoneList ?
        smartphoneList.map(smartphone =>
          <div className="smartphoneBox">
            <h2>{smartphone["brand"]} {smartphone["name"]}</h2>
            <img src={smartphone['imageLink']} className="smartphoneImage" alt="Imagem do Smartphone"></img>
            <div className="iconSpecBox">
              <div>
                <IconCalendarWeek />
                <h3>{smartphone["year"]}</h3>
              </div>
            </div>
            <div className="iconSpecBox">
              <div>
                {smartphone["systemName"].substring(0, smartphone["systemName"].indexOf(" ")) === "iOS" ? <IconBrandApple /> : <IconBrandAndroid />}
                <h3>{smartphone["systemName"]}</h3>
              </div>
            </div>
            <div className="iconSpecBox">
              <div>
                <IconDeviceMobile />
                <h3>{smartphone["screenSize"]} pol.</h3>
              </div>
            </div>
            <div className="iconSpecBox">
              <div>
                <IconCpu />
                <h3>{smartphone["cpu"]}</h3>
              </div>
            </div>
            <div className="iconSpecBox">
              <div>
                <IconCamera />
                <h3>x{smartphone["cameraNumber"]} ({smartphone["cameraMP"]}MP)</h3>
              </div>
            </div>
          </div>
        ) : <></>}
    </section>
    <button className="addButton" onClick={showModal}>+</button>
    <dialog ref={addModalRef}>
      <section className="addModal">
          {Object.entries(smartphoneInputs).map(([key, value]) => (
            <div key = {key} className="addBox">
            <label className="addLabel">{key}</label>
            <input value = {value} 
            onChange={(event) => handleInputChange(event, key)}
            className="addInput"></input>
            </div>
          ))}
      </section>
    </dialog>
    </div>
  );
}

export default App;
