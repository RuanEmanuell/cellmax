import { useEffect, useState } from 'react';
import { IconDeviceMobile, IconBrandApple, IconBrandAndroid, IconCalendarWeek, IconCpu, IconCamera } from '@tabler/icons-react';
import './App.css';

function App() {
  const [smartphoneList, setSmartphoneList] = useState(null);

  async function getData() {
    try {
      const response = await fetch("http://localhost:8080/smartphones/get");
      const data = await response.json();
      setSmartphoneList(data);
    } catch (error) {
      console.error(error);
    }
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
    </div>
  );
}

export default App;
