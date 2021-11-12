import { Button } from '@chakra-ui/button';
import { HStack, VStack } from '@chakra-ui/layout';
import { FC} from 'react';
import BasicLayout from '../components/layouts/basicLayout';
import { genQuizClient, genUserClient } from '../services/apiClients';
import { QuizStore } from '../stores/quizStore';

const Home: FC = () => {

    const getQuiz = async () => {
      const quizClient = await genQuizClient()
      quizClient.getQuiz(1)
    }

    const createQuiz = async () => {
      const quizClient = await genQuizClient()
      quizClient.createQuiz(QuizStore.quizes[0])
    }

    const updateQuiz = async () => {
      const quizClient = await genQuizClient()
      quizClient.updateQuiz(1, QuizStore.quizes[1])
    }

    const deleteQuiz = async () => {
      const quizClient = await genQuizClient()
      quizClient.deleteQuiz(1)
   }

   const getUser = async () => {
    const userClient = await genUserClient()
    userClient.getUser(1)
  }

  const createUser = async () => {
    const userClient = await genUserClient()
    userClient.createUser({id: 1, firstName: 'test', quizes: QuizStore.quizes})
  }

  const updateUser = async () => {
    const userClient = await genUserClient()
    userClient.updateUser(1, {id: 2, firstName: 'test2', quizes: QuizStore.quizes} )
  }

  const deleteUser = async () => {
    const userClient = await genUserClient()
    userClient.deleteUser(1)
 }
    

  return (
    <BasicLayout>
      <VStack spacing={2}>
        <HStack spacing={2}>
            <Button onClick={getQuiz} >Get Quiz</Button>
            <Button onClick={createQuiz} >Create Quiz</Button>
            <Button onClick={updateQuiz} >Update Quiz</Button>
            <Button onClick={deleteQuiz} >Delete Quiz</Button>
        </HStack>
        <HStack spacing={2}>
            <Button onClick={getUser} >Get User</Button>
            <Button onClick={createUser} >Create User</Button>
            <Button onClick={updateUser} >Update User</Button>
            <Button onClick={deleteUser} >Delete User</Button>
        </HStack>
        </VStack>
    </BasicLayout>
  );
}

export default Home;
