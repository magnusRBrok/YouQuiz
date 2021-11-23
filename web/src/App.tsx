import "./App.css";
import { FC } from "react";
import QuizPage from "./pages/QuizPage";
import NotFoundPage from "./pages/NotFoundPage";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import ServiceTestPage from "./pages/ServiceTestPage"
import { HashRouter, Route, Switch } from "react-router-dom";

const App: FC = () => {
  document.title = "YouQuiz";
  return (
    <HashRouter>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route exact path="/test" component={ServiceTestPage} />
        <Route exact path="/quiz" component={QuizPage} />
        <Route exact path="/login" component={LoginPage} />
        <Route exact path="/quiz/:quizId" component={QuizPage} />
        <Route exact component={NotFoundPage}></Route>
      </Switch>
    </HashRouter>
  );
};

export default App;
