import { FC } from "react";
import LoginForm from "./LoginForm";

import { Box } from "@chakra-ui/layout";

import { Button, SimpleGrid } from "@chakra-ui/react";
import { FaGoogle } from "react-icons/fa";
import DividerWithText from "./DividerWithText";

export const LoginCard: FC = () => {
  return (
    <>
      <Box p="6" rounded="md" mt="6" maxW="md" mx="auto">
        <LoginForm />
        <DividerWithText>or continue with</DividerWithText>
        <SimpleGrid mt="6" columns={1} spacing="3">
          <Button
            rightIcon={<FaGoogle />}
            color="currentColor"
            variant="outline"
          >
            Login with Google
          </Button>
        </SimpleGrid>
      </Box>
    </>
  );
};
