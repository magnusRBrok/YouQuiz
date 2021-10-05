import { Button, Stack, Text } from "@chakra-ui/react";
import { FC, useMemo } from "react";
import { useColors } from "../../hooks/useColors";
import { QuestionOption } from "../../model/questionOption";

interface optionProps {
  option: QuestionOption;
  answer: QuestionOption | undefined;
  onClick: (answer: QuestionOption) => void;
  i: number;
}

const Option: FC<optionProps> = ({ answer, option, onClick, i }) => {
  const { optionBg, optionBgHover, optionCorrect, optionIncorrect } =
    useColors();

  const alpha = Array.from(Array(26)).map((e, i) => i + 65);
  const alphabet = alpha.map((x) => String.fromCharCode(x));

  const bgColor = useMemo(
    () =>
      !answer
        ? optionBg
        : option.isCorrect
        ? optionCorrect
        : answer === option
        ? optionIncorrect
        : optionBg,
    [answer, option, optionBg, optionCorrect, optionIncorrect]
  );

  return (
    <Button
      bg={bgColor}
      _hover={!answer ? { backgroundColor: optionBgHover } : {}}
      fontWeight="normal"
      justifyContent="start"
      onClick={(e) => onClick(option)}
    >
      <Stack
          direction={"row"}
          spacing={6}>
        <Text>{alphabet[i]}</Text>
        <Text>{option.text}</Text>
      </Stack>
    </Button>
  );
};

export default Option;
