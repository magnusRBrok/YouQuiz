import {Box, HStack, Heading, Text, VStack, Button, Stack, Center, Progress} from '@chakra-ui/react';
import { observer } from 'mobx-react-lite';
import {FC, useCallback, useEffect} from 'react';
import {Link} from "react-router-dom";
import { useLocation, useParams } from "react-router";
import { Quiz } from '../../model/quiz';
import { QuizStore } from '../../stores/quizStore';

type completionProp = {
    correctAnswers: number,
    quiz: Quiz
}

const QuizCompletion: FC<completionProp> = observer(({correctAnswers, quiz}) => {

    return (
        <Box align="center"  borderWidth="1px" borderColor="black" borderRadius="xl" shadow="xl" p="10px">
            <Heading align={"center"}>{quiz.title}</Heading>
            <Text mt={75} fontSize="lg" align={"center"}>Congratulations you've finished the quiz</Text>
            <Text mt={100} fontSize="lg" align={"center"}>You answered {correctAnswers} out of {quiz.questions.length} questions</Text>
            <Box w="40vw" mt="20px" align="left" >
                <Progress borderWidth="1px" borderColor="black" borderRadius="lg" size="lg"  hasStripe value={correctAnswers/quiz.questions.length*100} />
            </Box>
            <Center  mt={150}>
                <Link to={"/"}><Button onClick={()=> QuizStore.newQuizSession(quiz)} colorScheme="blue">Close quiz</Button></Link>
            </Center>
        </Box>
    )
});

export default QuizCompletion;
