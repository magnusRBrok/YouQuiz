import { FC, useState } from "react";
import BasicLayout from "../components/layouts/basicLayout";
import { LoginComponent } from "../components/login/LoginComponent";
import { SignupComponent } from "../components/signup/SignupComponent";

const LoginPage: FC = () => {
  const [showLogin, setShowLogin] = useState<boolean>(true);

  return (
    <BasicLayout>
      {showLogin ? (
        <LoginComponent showLogin={(showLogin) => setShowLogin(showLogin)} />
      ) : (
        <SignupComponent showLogin={(showLogin) => setShowLogin(showLogin)} />
      )}
    </BasicLayout>
  );
};

export default LoginPage;
