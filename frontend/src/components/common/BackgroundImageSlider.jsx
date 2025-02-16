import bg from "../../assets/images/bg.jpg"
import bg1 from "../../assets/images/bg1.png"
import bg2 from "../../assets/images/bg2.jpg"
import {useState} from "react";
import {Carousel} from "react-bootstrap";

const BackgroundImageSlider = () => {
    const backgroundImages = [bg1, bg, bg2]
    const [index, setIndex] = useState(0)


    function handleSelect(selectedIndex) {
        setIndex(selectedIndex)
    }

    return (
        <div className="background-slider">
            <Carousel activeIndex={index} onSelect={handleSelect} interval={20000}>
                {
                    backgroundImages.map((background, index) => {
                        return (
                            <Carousel.Item key={index}>
                                <img
                                    className="d-block w-100"
                                    src={background}
                                    alt="Slide Imgae"
                                />
                            </Carousel.Item>
                        );
                    })
                }
            </Carousel>
        </div>

    );
};

export default BackgroundImageSlider;