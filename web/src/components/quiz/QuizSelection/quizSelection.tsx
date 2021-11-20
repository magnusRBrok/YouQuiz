import {
  Box,
  Center,
  Heading,
  List,
  ListItem,
  SimpleGrid,
  Stack,
} from "@chakra-ui/layout";
import { Skeleton } from "@chakra-ui/skeleton";
import { observer } from "mobx-react-lite";
import { FC, useEffect, useState } from "react";
import { Quiz } from "../../../model/quiz";
import { AuthBase } from "../../../services/clientBase";
import { QuizClient } from "../../../services/clients/quizClient";
import { QuizStore, QuizStoreImpl } from "../../../stores/quizStore";
import CreateRandomQuizForm from "../createRandomQuizForm";
import QuizSelectionItem from "./quizSelectionItem";

const QuizSelection: FC = observer(() => {
  const [addingQuiz, setAddingQuiz] = useState(false);

  useEffect(() => {
    let client = new QuizClient(new AuthBase(""));
    client
      .getAllQuizzes()
      .then((res) => {
        QuizStore.setQuizzes(res);
      })
      .catch((e) => {
        console.error(e);
      });
  }, []);

  return (
    <Box height="full" width="full" justifyContent="center">
      <Heading>Select a quiz</Heading>
      <SimpleGrid
        width="full"
        minChildWidth="240px"
        spacing={7}
        mt={10}
        mb={10}
      >
        {QuizStore.quizes.map((quiz) => (
          <Center key={quiz.id}>
            <QuizSelectionItem quiz={quiz} />
          </Center>
        ))}
        {addingQuiz && (
          <Skeleton>
            <QuizSelectionItem
              quiz={{
                id: -1,
                createdById: -1,
                title: " ",
                description: "",
                category: "",
                createdBy: { id: -1, first_name: "" },
                questions: [],
              }}
            />
          </Skeleton>
        )}
      </SimpleGrid>
      <Heading>Generate quiz</Heading>
      <Box maxW="md">
        <CreateRandomQuizForm
          setSubmitting={(isSubmitting) => setAddingQuiz(isSubmitting)}
        />
      </Box>
    </Box>
  );
});

export default QuizSelection;
