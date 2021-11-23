import { Box, Heading, Text } from "@chakra-ui/layout";
import { FC } from "react";
import { LoginCard } from "./LoginCard";

interface LoginComponentProps {
  showLogin: (showLogin: boolean) => void;
}

export const LoginComponent: FC<LoginComponentProps> = ({ showLogin }) => {
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
          <Text
            as="span"
            cursor="pointer"
            textDecoration="underline"
            onClick={() => showLogin(false)}
          >
            Sign up!
          </Text>
        </Text>
      </Box>
      <LoginCard />
    </>
  );
};
