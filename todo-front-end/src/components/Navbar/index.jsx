
import styles from './index.module.css'
import logo from '../../assets/todopic.png'

const Navbar = () => {
    return(
        <>
            <div className={styles.navbar}>
                <div className={styles.logoSection}>
                    <img src={logo} alt={"todo list logo"}/>
                </div>

                <div className={styles.midSection}>
                    <p>Dashboard</p>
                    <p>Calender</p>
                    <p>Services</p>

                </div>

                <div className={styles.buttons}>
                    <button style={{
                        color: "#ff5b1a",
                        background: "none",
                        border: "none",
                        fontSize: "18px",
                        fontWeight: "500"
                    }}>Login
                    </button>

                    <button
                        style={{paddingTop: "10px",
                            paddingBottom: "10px",
                            paddingLeft: "25px",
                            paddingRight: "25px",
                            marginTop: "10px",
                        borderRadius: "4px",
                        background: "#ff5b1a",
                        color: "#fff",
                        border: "none",
                    fontSize: "18px",}}
                    >Sign up
                    </button>
                </div>

            </div>
        </>
    )
}

export default Navbar