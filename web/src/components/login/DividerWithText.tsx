import { Box, Divider, Flex, Text, useColorModeValue } from "@chakra-ui/react";
import { FC } from "react";

const DividerWithText: FC = (props) => {
  const { children } = props;
  return (
    <Flex align="center" color="gray.300" mt="6">
      <Box flex="1">
        <Divider borderColor="currentcolor" />
      </Box>
      <Text
        as="span"
        px="3"
        color={useColorModeValue("gray.600", "gray.400")}
        fontWeight="medium"
      >
        {children}
      </Text>
      <Box flex="1">
        <Divider borderColor="currentcolor" />
      </Box>
    </Flex>
  );
};

export default DividerWithText;
