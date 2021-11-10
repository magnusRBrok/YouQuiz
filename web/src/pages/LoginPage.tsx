import { FC } from "react";
import BasicLayout from "../components/layouts/basicLayout";
import { LoginComponent } from "../components/login/LoginComponent";

const LoginPage: FC = () => {
  return (
    <BasicLayout>
      <LoginComponent></LoginComponent>
    </BasicLayout>
  );
};

export default LoginPage;
