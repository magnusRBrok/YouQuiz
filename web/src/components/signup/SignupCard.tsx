import { Button } from "@chakra-ui/button";
import { Box, SimpleGrid } from "@chakra-ui/layout";
import React, { FC } from "react";
import { FaGoogle } from "react-icons/fa";
import DividerWithText from "../login/DividerWithText";
import { SignupForm } from "./SignupForm";

export const SignupCard: FC = () => {
  return (
    <>
      <Box p="6" rounded="md" mt="6" maxW="md" mx="auto">
        <SignupForm />
        <DividerWithText>or continue with</DividerWithText>
        <SimpleGrid mt="6" columns={1} spacing="3">
          <Button
            rightIcon={<FaGoogle />}
            color="currentColor"
            variant="outline"
          >
            Google
          </Button>
        </SimpleGrid>
      </Box>
    </>
  );
};
