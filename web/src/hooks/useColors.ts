import { useColorModeValue } from "@chakra-ui/react";

export const useColors = () => {
  const headerBg = useColorModeValue("blue.300", "blue.800");
  const optionBg = useColorModeValue("blue.200", "blue.700");
  const optionBgHover = useColorModeValue("blue.300", "blue.800");
  const optionCorrect = useColorModeValue("green.400", "green.600");
  const optionIncorrect = useColorModeValue("red.400", "red.600");
  const primaryColorHover = useColorModeValue("blue.100", "blue.700");

  return {
    headerBg,
    optionBg,
    optionBgHover,
    optionCorrect,
    optionIncorrect,
    primaryColorHover,
  };
};
