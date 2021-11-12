import {
  Box,
  Center,
  Heading,
  List,
  ListItem,
  SimpleGrid,
  Stack,
} from "@chakra-ui/layout";
import { observer } from "mobx-react-lite";
import { FC, useEffect, useState } from "react";
import { Quiz } from "../../../model/quiz";
import { AuthBase } from "../../../services/clientBase";
import { QuizClient } from "../../../services/clients/quizClient";
import { QuizStore, QuizStoreImpl } from "../../../stores/quizStore";
import QuizSelectionItem from "./quizSelectionItem";

const QuizSelection: FC = observer(() => {
  const [quizzes, setQuizzes] = useState<Quiz[]>([]);

  useEffect(() => {
    let client = new QuizClient(new AuthBase(""));
    client
      .getAllQuizzes()
      .then((res) => {
        //setQuizzes(res);
        QuizStore.setQuizzes(res);
      })
      .catch((e) => {
        console.error(e);
      });
  }, []);

  return (
    <Box height="full" width="full" justifyContent="center">
      <Heading>Select a quiz</Heading>
      <SimpleGrid width="full" minChildWidth="240px" spacing="10px">
        {QuizStore.quizes.map((quiz) => (
          <Center>
            <QuizSelectionItem key={quiz.id} quiz={quiz} />
          </Center>
        ))}
      </SimpleGrid>
    </Box>
  );
});

export default QuizSelection;
