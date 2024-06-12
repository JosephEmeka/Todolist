
import styles from './index.module.css'
import logo from '../../assets/TO.png'

const Navbar = () => {
    return(
        <>
            <div className={styles.navbar}>
                <div className={styles.logoSection}>
                    <img src={logo} alt={"todo list logo"}/>
                </div>

                <div className={styles.midSection}>

                </div>

                <div className={styles.login}>
                    <button style={{
                        color: "#c2ba1d",
                        background: "none",
                        border: "none",
                        fontSize: "16px",
                        fontWeight: "500"
                    }}>Login
                    </button>

                    <button style={{
                        color: "#c2ba1d",
                        background: "none",
                        border: "none",
                        fontSize: "16px",
                        fontWeight: "500"
                    }}>Sign up
                    </button>
                </div>

            </div>
        </>
    )
}

export default Navbar