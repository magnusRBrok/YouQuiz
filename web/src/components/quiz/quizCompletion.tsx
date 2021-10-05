import {Box, HStack, Text, VStack, Button, Stack, Center} from '@chakra-ui/react';
import {FC} from 'react';
import {Link} from "react-router-dom";

interface completionProp {
    correctAnswers: number,
    questionAmount: number,
    quizName?: string
}

const QuizCompletion: FC<completionProp> = ({correctAnswers, questionAmount, quizName}) => {
    return (
        <Box>
            <Text align={"center"}>{quizName}</Text>
            <Text mt={75} align={"center"}>Tillykke du er færdig</Text>
            <Text mt={100} align={"center"}>{correctAnswers}/{questionAmount} korrekte svar</Text>
            <Center mt={150}>
                <Link to={"/"}><Button>Afslut Quiz</Button></Link>
            </Center>
        </Box>
    )
}

export default QuizCompletion;
