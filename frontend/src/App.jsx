import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./components/home/Home.jsx";
import BackgroundImageSlider from "./components/common/BackgroundImageSlider.jsx";

function App() {
    return (
        <main className="main">
            <BackgroundImageSlider>
                <div className="text-info">Welcome to the brand new project</div>
                <Home/>
            </BackgroundImageSlider>
        </main>
    );
}

export default App;
