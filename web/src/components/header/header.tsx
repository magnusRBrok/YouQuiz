import { Box, Container, Flex, Heading, HStack } from "@chakra-ui/layout";
import { FC } from "react";
import { useColors } from "../../hooks/useColors";
import ColorModeBtn from "../buttons/colorModeBtn";
import NavBar from "./navbar";

const Header: FC = ({ children }) => {
  const appName = "YouQuiz";
  const { headerBg } = useColors();

  return (
    <header>
      <Box bg={headerBg}>
        <Container maxW="container.lg" p={0}>
          <Flex justifyContent="space-between" paddingX={[2, 5, 10]} py={3}>
            <HStack spacing={10}>
              <Heading as="a" href="/" size="md">{appName}</Heading>
              <NavBar />
            </HStack>
            <ColorModeBtn />
          </Flex>
        </Container>
      </Box>
    </header>
  );
};

export default Header;
