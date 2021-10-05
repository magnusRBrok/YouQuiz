import { useColorModeValue } from "@chakra-ui/react";

export const useColors = () => {
  const headerBg = useColorModeValue("gray.200", "gray.700");
  const optionBg = useColorModeValue("gray.200", "gray.700");
  const optionBgHover = useColorModeValue("gray.300", "gray.800");
  const optionCorrect = useColorModeValue("green.400", "green.600");
  const optionIncorrect = useColorModeValue("red.400", "red.600");
    const primaryColorHover = useColorModeValue("gray.100", "grey.700");

  return {
    headerBg,
    optionBg,
    optionBgHover,
    optionCorrect,
    optionIncorrect,
    primaryColorHover,
  };
};
