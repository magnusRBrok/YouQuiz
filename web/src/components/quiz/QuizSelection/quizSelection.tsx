import { Box, Center, Heading, SimpleGrid } from "@chakra-ui/layout";
import { observer } from "mobx-react-lite";
import { FC } from "react";
import { QuizStore } from "../../../stores/quizStore";
import QuizSelectionItem from "./quizSelectionItem";

const QuizSelection: FC = observer(() => {
  return (
    <Box height="full" width="full" justifyContent="center">
      <Heading>Select a quiz</Heading>
      <SimpleGrid width="full" minChildWidth="240px" spacing="10px">
        {QuizStore.quizes.map((quiz) => (
          <Center key={quiz.id}>
            <QuizSelectionItem quiz={quiz} />
          </Center>
        ))}
        {QuizStore.quizes.map((quiz) => (
          <Center key={quiz.id}>
            <QuizSelectionItem quiz={quiz} />
          </Center>
        ))}
        {QuizStore.quizes.map((quiz) => (
          <Center key={quiz.id}>
            <QuizSelectionItem quiz={quiz} />
          </Center>
        ))}
        {QuizStore.quizes.map((quiz) => (
          <Center key={quiz.id}>
            <QuizSelectionItem quiz={quiz} />
          </Center>
        ))}
      </SimpleGrid>
    </Box>
  );
});

export default QuizSelection;
