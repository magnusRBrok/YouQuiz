import { Box, Heading, Text } from "@chakra-ui/layout";
import React, { FC } from "react";
import { SignupCard } from "./SignupCard";

interface SignupComponentProps {
  showLogin: (showLogin: boolean) => void;
}

export const SignupComponent: FC<SignupComponentProps> = ({ showLogin }) => {
  return (
    <>
      <Box>
        <Heading textAlign="center" size="xl" fontWeight="extrabold">
          Create your account
        </Heading>
        <Text
          mt="4"
          mb="8"
          align="center"
          mx="auto"
          maxW="md"
          fontWeight="medium"
        >
          <Text as="span">Already have an account? </Text>
          <Text
            as="span"
            cursor="pointer"
            textDecoration="underline"
            onClick={() => showLogin(true)}
          >
            Sign in here!
          </Text>
        </Text>
      </Box>
      <SignupCard />
    </>
  );
};
