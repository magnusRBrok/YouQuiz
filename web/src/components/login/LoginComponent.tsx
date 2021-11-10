import { Box, Heading, Text } from "@chakra-ui/layout";
import { Link } from "react-router-dom";
import { FC } from "react";
import { LoginCard } from "./LoginCard";

export const LoginComponent: FC = () => {
  return (
    <>
      <Box>
        <Heading textAlign="center" size="xl" fontWeight="extrabold">
          Sign in to your account
        </Heading>
        <Text
          mt="4"
          mb="8"
          align="center"
          mx="auto"
          maxW="md"
          fontWeight="medium"
        >
          <Text as="span">Don&apos;t have an account? </Text>
          <Link to={"/signup"}>Sign up!</Link>
        </Text>
      </Box>
      <LoginCard />
    </>
  );
};
