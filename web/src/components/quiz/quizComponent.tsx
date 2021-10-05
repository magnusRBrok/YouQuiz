import {Button} from "@chakra-ui/button";
import {Heading, HStack, Stack, Text} from "@chakra-ui/layout";
import {observer} from "mobx-react-lite";
import {FC, useCallback, useEffect, useMemo, useState} from "react";
import {QuizStore} from "../../stores/quizStore";
import QuestionComponent from "./questionComponent";
import QuizCompletion from "./quizCompletion";

const QuizComponent: FC = observer(() => {
    const quiz = QuizStore.getQuiz(1); //TODO: quiz id should be read from route

    if (!quiz) return <></>; //TODO: Render something that indicates the quiz cannot be found

    useEffect(() => {
        QuizStore.newQuizSession(quiz);
    }, [quiz]);

    const [question, setQuestion] = useState(quiz?.questions[0]);

    const currentQuestionIndex = useMemo(
        () => quiz?.questions.findIndex((q) => q === question) ?? 0,
        [quiz, question]
    );

    const [isQuizCompleted, setIsQuizCompleted] = useState(false);

    const [showCompletePage, setShowCompletePage] = useState(false);

    const changeQuestion = useCallback(
        (index: number) => {
            quiz?.questions[index] && setQuestion(quiz.questions[index]);

            if (index == quiz.questions.length - 1) setIsQuizCompleted(true)
        },
        [quiz, setQuestion]
    );

    if (showCompletePage) return <QuizCompletion questionAmount={quiz.questions.length} correctAnswers={QuizStore.correctAnswers} quizName={quiz.title}/>

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
                        <Button onClick={() => changeQuestion(currentQuestionIndex - 1)}>
                            Previous
                        </Button>
                        {isQuizCompleted ?
                            <Button onClick={() => setShowCompletePage(true)}>
                            Afslut
                        </Button> : <Button onClick={() => changeQuestion(currentQuestionIndex + 1)}>
                            Next
                        </Button> }
                    </HStack>
                </Stack>
            )}
        </>
    );
});

export default QuizComponent;
