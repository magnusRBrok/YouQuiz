import { Heading, List, ListItem, Stack } from '@chakra-ui/layout';
import { observer } from 'mobx-react-lite';
import { FC } from 'react';
import { QuizStoreImpl } from '../stores/quizStore';

interface Props {
    quizStore: QuizStoreImpl
}

const QuizSelection: FC<Props> = observer(({ quizStore }) => {

    return (
        <>
            {quizStore.quizes.map(quiz => (
                /* This is just temporary for demonstration. Implement actual templating/layout */
                <Stack spacing={5} key={quiz.id}>
                    <Heading>{quiz.title}</Heading>
                        <List>
                            <ListItem>Category: {quiz.category}</ListItem>
                            <ListItem>Description: {quiz.description}</ListItem>
                        </List>
                </Stack>
            ))}
        </>
    );
});

export default QuizSelection;