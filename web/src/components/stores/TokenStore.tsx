import {observable} from "mobx";

const loginStates = {LOGGING_IN: "Loading", LOGGED_OUT: "LoggedOut", LOGGED_IN: "LoggedIn"};

class TokenStore {

    state = loginStates.LOGGED_OUT
    token: string | null = "";
    loginData = observable({email: "", password: ""})


    constructor() {
        this.token = localStorage.getItem("youQuizToken")

    }

    login() {
        this.state = loginStates.LOGGING_IN
        console.log("State changed to: " + this.state)
        fetch("http://localhost:8080/rest/login", {
            method: "POST",
            body: JSON.stringify(this.loginData),
            headers: {
                'content-Type': 'application/json'
            }
        }).then(
            (response) => response.text().then(
                (token) => {
                    console.log("Got token: " + token);
                    this.token = token;
                    localStorage.setItem("youQuizToken", token);
                    this.state = loginStates.LOGGED_IN
                    console.log("State changed to: " + this.state)
                })
        ).catch(() => {
            this.state = loginStates.LOGGED_OUT
            console.log("State changed to: " + this.state)
        })
    }
}


export const tokenStore = new TokenStore();
