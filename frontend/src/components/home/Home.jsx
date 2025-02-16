import {Button, Card, Col, Container, ListGroup, Row} from "react-bootstrap";

//
import d5 from "../../assets/images/d5.jpg";
import veterinarian from "../../assets/images/veterinarian-1.jpg";

const Home = () => {
    return (
        <Container className="home-container mt-5">
            <Row>
                <Col md={6} className="mb-3">
                    <Card className="card-container">
                        <Card.Img className="hero-image" variant="top" src={d5} alt="About Us"/>
                        <Card.Body>
                            <h2 className="text-info">Who are you</h2>
                            <Card.Title>Comprehensive care for your furry friends</Card.Title>
                            <Card.Text>
                                At Universal Pet Care, we believe every pet deserves the best.
                                Our team of dedicated professionals is here to ensure your pet&#39;s
                                health and happiness through comprehensive veterinary services.
                                With decades of combined experience, our veterinarians and
                                support staff are committed to providing personalized care
                                tailored to the unique needs of each pet.
                            </Card.Text>
                            <Card.Text>
                                We offer a wide range of services, from preventive care and
                                routine check-ups to advanced surgical procedures and emergency
                                care. Our state-of-the-art facility is equipped with the latest
                                in veterinary technology, which allows us to deliver
                                high-quality care with precision and compassion.
                            </Card.Text>
                            <Button variant="outline-info">Meet Our Veterinarians</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={6} className="mb-3">
                    <Card className="card-container">
                        <Card.Img variant="top" src={veterinarian} alt="About Us" className="hero-image" />
                        <Card.Body>
                            <h2 className="text-info">Our Services</h2>
                            <Card.Title>Comprehensive care for your furry friends</Card.Title>
                            <ListGroup className="services-list">
                                <ListGroup.Item>Veterinary Check-ups</ListGroup.Item>
                                <ListGroup.Item>Emergency Surgery</ListGroup.Item>
                                <ListGroup.Item>Pet Vaccinations</ListGroup.Item>
                                <ListGroup.Item>Dental Care</ListGroup.Item>
                                <ListGroup.Item>Spaying and Neutering</ListGroup.Item>
                                <ListGroup.Item>And many more...</ListGroup.Item>
                            </ListGroup>
                            <Card.Text className="mt-3">
                                From routine check-ups to emergency surgery, our full range of
                                veterinary services ensures your pet&#39;s health is in good hands.
                            </Card.Text>
                            <Button variant="outline-info">Meet Our Veterinarians</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <div className="card mb-5">
                <h4>
                    What people are saying about
                    <span className="text-info"> Universal Pet Care </span> Veterinarians
                </h4>
                <hr/>
                <p className="text-center">Here, we are going to be sliding veterinarians across</p>
            </div>
        </Container>
    );
};

export default Home;
