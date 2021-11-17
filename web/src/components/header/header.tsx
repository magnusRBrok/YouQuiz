import { Box, Container, Flex, Heading, HStack } from "@chakra-ui/layout";
import { FC } from "react";
import { Link } from "react-router-dom";
import { useColors } from "../../hooks/useColors";
import ColorModeBtn from "../buttons/colorModeBtn";
import NavBar from "./navbar";
import NavbarMenu from "./navbarMenu";

const Header: FC = ({ children }) => {
  const appName = "YouQuiz";
  const { headerBg } = useColors();

  return (
    <header>
      <Box bg={headerBg}>
        <Container maxW="container.lg" p={0}>
          <Flex justifyContent="space-between" paddingX={[2, 5, 10]} py={3}>
            <HStack spacing={10}>
              <Link to="/">
                <Heading size="md">{appName}</Heading>
              </Link>
              <NavBar />
            </HStack>
            <HStack>
              <ColorModeBtn />
              <NavbarMenu />
            </HStack>
          </Flex>
        </Container>
      </Box>
    </header>
  );
};

export default Header;
