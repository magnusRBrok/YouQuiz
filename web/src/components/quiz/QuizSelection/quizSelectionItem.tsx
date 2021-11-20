import { IconButton } from "@chakra-ui/button";
import { Box, Flex, Heading, Stack, Text } from "@chakra-ui/layout";
import { Skeleton } from "@chakra-ui/skeleton";
import { AnimatePresence, motion } from "framer-motion";
import { observer } from "mobx-react-lite";
import { FC, useCallback, useEffect, useState } from "react";
import { BsX } from "react-icons/bs";
import { Link } from "react-router-dom";
import { useColors } from "../../../hooks/useColors";
import { Quiz } from "../../../model/quiz";
import { AuthBase } from "../../../services/clientBase";
import { QuizClient } from "../../../services/clients/quizClient";
import { QuizStore } from "../../../stores/quizStore";
interface Props {
  quiz: Quiz;
}

const MotionBox = motion(Box);

const QuizSelectionItem: FC<Props> = observer(({ quiz }) => {
  const { quizItemBg, quizItemBgHover } = useColors();
  const [deleteVisible, setDeleteVisible] = useState(false);
  const [deleting, setDeleting] = useState(false);

  const [visible, setVisible] = useState(false);

  const onDelete = useCallback(async () => {
    setDeleting(true);
    try {
      let client = new QuizClient(new AuthBase(""));
      await client.deleteQuiz(quiz.id);
      let quizzes = await client.getAllQuizzes();
      setVisible(false);
      QuizStore.setQuizzes(quizzes);
    } catch (e) {
      console.log(e);
    }
    setDeleting(false);
  }, [setDeleting, quiz.id]);

  useEffect(() => {
    setVisible(true);
  }, []);

  return (
    <AnimatePresence>
      {visible && (
        <Skeleton isLoaded={!deleting} h="full" w="full" borderRadius="md">
          <Link to={"quiz/" + quiz.id}>
            <MotionBox
              animate={{
                y: 0,
                opacity: 0.8,
              }}
              transition={{
                opacity: { duration: 1 },
              }}
              style={{
                opacity: 0,
                y: 30,
              }}
              whileHover={{
                opacity: 1,
                scale: 1.05,
                transition: { duration: 0.2 },
                y: -10,
              }}
              exit={{
                opacity: 0,
              }}
              cursor="pointer"
              onMouseEnter={(e: any) => setDeleteVisible(true)}
              onMouseLeave={(e: any) => setDeleteVisible(false)}
              width="full"
              h="10rem"
              bgGradient="linear(to-b, white, gray.100)"
              p={2}
              borderRadius="md"
              boxShadow="xl"
            >
              <Stack
                borderRadius={5}
                borderColor="black"
                spacing={5}
                key={quiz.id}
                h="full"
              >
                <Flex justifyContent="space-between">
                  <Stack spacing={0}>
                    <Heading size="md">{quiz.title}</Heading>
                    <Text fontSize="xs">{`Category: ${quiz.category} | By: ${quiz.createdBy?.first_name}`}</Text>
                    <Text fontSize="xs">{`Questions: ${quiz.questions.length}`}</Text>
                  </Stack>
                  <IconButton
                    hidden={!deleteVisible}
                    aria-label="delete quiz"
                    icon={<BsX />}
                    size="xs"
                    variant="ghost"
                    onClick={(e) => {
                      e.preventDefault();
                      onDelete();
                    }}
                  />
                </Flex>
                <Flex h="full">
                  <Text fontSize="sm">{quiz.description}</Text>
                </Flex>
              </Stack>
            </MotionBox>
          </Link>
        </Skeleton>
      )}
    </AnimatePresence>
  );
});

export default QuizSelectionItem;
