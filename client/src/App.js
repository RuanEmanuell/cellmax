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
  const [editingMode, setEditingMode] = useState(false);

  const addModalRef = useRef(null);

  async function getData() {
    try {
      const response = await fetch("http://localhost:8080/get");
      const data = await response.json();
      setSmartphoneList(data);
    } catch (error) {
      console.error(error);
    }
  }

  async function addOrEditSmartphone(){
    try {
      const url = editingMode ? "http://localhost:8080/put" : "http://localhost:8080/post";
      
      // Adiciona o ID à solicitação POST se estiver no modo de edição
      if (editingMode) {
        smartphoneInputs.id = smartphoneInputs.id || ''; // Garante que o ID esteja definido
      } else {
        delete smartphoneInputs.id; // Remove o ID da solicitação POST se estiver criando um novo smartphone
      }
  
      await fetch(url, {
        method: editingMode ? "PUT" : "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(smartphoneInputs)
      });
      await getData();
      setEditingMode(false);
      closeModal();
    } catch (error) {
      console.error(error);
    }
  }
  

  function showModal() {
    addModalRef.current.showModal();
  }

  function closeModal(){
    addModalRef.current.close();
    setSmartphoneInputs({
      name: "",
      brand: "",
      year: "",
      systemName: "",
      screenSize: "",
      cpu: "",
      cameraNumber: "",
      cameraMP: "",
      imageLink: ""
    });
  }

  function showEditModal(smartphone){
    setEditingMode(true);
    setSmartphoneInputs(smartphone);
    showModal();
  }

  function handleInputChange(event, key) {
    const value = event.target.value;
    setSmartphoneInputs(prev => ({
      ...prev,
      [key]: value
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
            <div className="smartphoneBox" key={smartphone["id"]} onClick={() => showEditModal(smartphone)}>
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
          <img src = {smartphoneInputs.imageLink ? smartphoneInputs.imageLink : "https://t4.ftcdn.net/jpg/03/96/94/97/360_F_396949753_wXrV9GJ4oWrKUOmTJw6XLaEVdmtrSjCF.jpg"} className="addImage"></img>
          {Object.entries(smartphoneInputs).map(([key, value]) => (
            <>
            {key === "id" ? <></> :               <div key={key} className="addBox">
              <label className="addLabel">{key}</label>
              <input 
                required 
                value={value}
                onChange={(event) => handleInputChange(event, key)}
                className="addInput"></input>
            </div>}
            </>
          ))}
          <div className="buttonsBox">
            <button className="defaultButton" style = {{backgroundColor: "rgb(0, 110, 255)"}} onClick={addOrEditSmartphone}>Confirmar</button>
            <button className="defaultButton" style = {{backgroundColor: "red"}} onClick = {closeModal}>Cancelar</button>
          </div>
        </section>
      </dialog>
    </div>
  );
}

export default App;
