import { Button } from '@chakra-ui/button';
import { Link } from '@chakra-ui/layout';
import { FC } from 'react';
import BasicLayout from '../components/layouts/basicLayout';
import QuizSelection from '../components/quiz/QuizSelection/quizSelection';


const Home: FC = () => {

  return (

    <BasicLayout>
      <QuizSelection />
    </BasicLayout>

  );
}

export default Home;
