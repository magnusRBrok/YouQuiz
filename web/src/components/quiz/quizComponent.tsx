import {Button} from "@chakra-ui/button";
import {Heading, HStack, Stack, Text} from "@chakra-ui/layout";
import {observer} from "mobx-react-lite";
import {FC, useCallback, useEffect, useMemo, useState} from "react";
import { Quiz } from "../../model/quiz";
import {QuizStore} from "../../stores/quizStore";
import QuestionComponent from "./questionComponent";
import QuizCompletion from "./quizCompletion";

type Props = {
    quiz: Quiz
}

const QuizComponent: FC<Props> = observer(({quiz}) => {


    useEffect(() => {
        QuizStore.newQuizSession(quiz);
    }, [quiz]);

    const [question, setQuestion] = useState(quiz.questions[0]);
    const [showCompletePage, setShowCompletePage] = useState(false);

    const currentQuestionIndex = useMemo(
        () => quiz?.questions.findIndex((q) => q === question) ?? 0,
        [quiz, question]
    );



    const changeQuestion = useCallback(
        (index: number) => {
            quiz?.questions[index] && setQuestion(quiz.questions[index]);
        },
        [quiz, setQuestion]
    );

    if (showCompletePage) return <QuizCompletion quiz={quiz} correctAnswers={QuizStore.correctAnswers} />

    return (
        <>
            {quiz && question && (
                <Stack>
                    <Heading size="md">{quiz.title}</Heading>
                    <QuestionComponent question={question}/>
                    <Text>{`Question ${currentQuestionIndex + 1}/${
                        quiz.questions.length
                    }`}</Text>
                    <Text>{QuizStore.correctAnswers} correct</Text>
                    <HStack>
                        <Button isDisabled={currentQuestionIndex == 0} colorScheme="blue" onClick={() => changeQuestion(currentQuestionIndex - 1)}>
                            Previous
                        </Button> 
                        {quiz.questions.length != currentQuestionIndex+1 &&
                            <Button isDisabled={(QuizStore.quizSession?.answers.size ?? 0) < currentQuestionIndex+1}  colorScheme="blue" onClick={() => changeQuestion(currentQuestionIndex + 1)}>
                                Next
                            </Button>
                        }
                        {QuizStore.quizSession?.answers.size == quiz.questions.length && <Button isDisabled={(QuizStore.quizSession?.answers.size ?? 0) < currentQuestionIndex+1} colorScheme="green" onClick={() => setShowCompletePage(true)}>
                            Results
                        </Button>}
                    </HStack>
                </Stack>
            )}
        </>
    );
});

export default QuizComponent;
