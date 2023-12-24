import "./Footer.css"
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faInstagram, faFacebook } from '@fortawesome/free-brands-svg-icons';

export const Footer = () => {

    return (
        <footer>
            <div className="content">
                <div className="left-section">
                    <h1>CONTACT</h1>
                    <p>
                        Help us enhance your experience with our web application.
                        We value your feedback and would love to hear your thoughts.
                        Share your opinions on how we can improve and better cater to your needs.
                    </p>
                </div>
                <div className="right-section">
                    <div className="form-container">
                        <form>
                            <div>
                                <textarea name="description" placeholder="Write your thoughts..." required></textarea>
                            </div>
                            <div className="input-container">
                                <input type="text" name="email" placeholder="Enter your e-mail..." required/><br/>
            

                                <button type="submit" id="submit-button">SUBMIT</button>
                            </div>

                        </form>
                    </div>
                    <div className="socials">
                        <FontAwesomeIcon icon={faInstagram} size="2x" />
                        <FontAwesomeIcon icon={faFacebook} size="2x" />
                    </div>

                </div>

            </div>
            <div className="copyright">
                © 2023 Wine.mk
            </div>
        </footer>
    )
}
