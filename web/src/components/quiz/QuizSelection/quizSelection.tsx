import { Image } from "@chakra-ui/image";
import {
  Box,
  Center,
  Heading,
  Link,
  SimpleGrid,
  Stack,
  Text,
} from "@chakra-ui/layout";
import { Skeleton } from "@chakra-ui/skeleton";
import { motion } from "framer-motion";
import { observer } from "mobx-react-lite";
import { FC, useEffect, useState } from "react";
import { AuthBase } from "../../../services/clientBase";
import { QuizClient } from "../../../services/clients/quizClient";
import { QuizStore } from "../../../stores/quizStore";
import CreateRandomQuizForm from "../createRandomQuizForm";
import QuizSelectionItem from "./quizSelectionItem";

const QuizSelection: FC = observer(() => {
  const [addingQuiz, setAddingQuiz] = useState(false);
  const MotionImage = motion(Image);
  const MotionBox = motion(Box);

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
      <Heading mb={10}>Generate quiz</Heading>
      <SimpleGrid width="full" minChildWidth="300px" spacing={7}>
        <Stack spacing={3}>
          <Text>
            Our quiz generator is powered by{" "}
            <Link color="blue.600" href="https://quizapi.io/" isExternal>
              QuizAPI.io
            </Link>
            , that lets you generate quizzes about various technologies in IT.
          </Text>
          <Text>
            When generating a quiz, you may optionally specify a limit for the
            number of questions, a category, and/or a difficulty. If left
            unspecified, they will be chosen randomly.
          </Text>
          <Text>
            If the generation fails and you have specified a category and
            difficulty, please try changing the difficulty, as there may not
            exist enough questions from QuizAPI to to provide a full quiz.
          </Text>
          <Image boxSize="sm" src="addpost.svg" />
        </Stack>
        <Box>
          <CreateRandomQuizForm
            setSubmitting={(isSubmitting) => setAddingQuiz(isSubmitting)}
          />
        </Box>
      </SimpleGrid>
    </Box>
  );
});

export default QuizSelection;
