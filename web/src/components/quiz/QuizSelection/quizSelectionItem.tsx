import { Box, Flex, Heading, List, ListItem, Stack } from '@chakra-ui/layout';
import { observer } from 'mobx-react-lite';
import { FC } from 'react';
import { Link } from 'react-router-dom';
import { useColors } from '../../../hooks/useColors';
import {Quiz} from "../../../model/quiz";

interface Props {
    quiz: Quiz
}

const QuizSelectionItem: FC<Props> = observer(({ quiz }) => {
    const { headerBg, primaryColorHover } = useColors();


    return (
        <Link to={"quiz/"+quiz.id}>
            <Box cursor="pointer" _hover={{ bgColor: primaryColorHover }} width="250px" h="fit-content" bgColor={headerBg} m={5} p={2} borderRadius="md" boxShadow="xl" >
                <Stack borderRadius={5} borderColor="black" spacing={5} key={quiz.id}>
                    <Heading size="md">{quiz.title}</Heading>
                    <List>
                        <ListItem>Category: {quiz.category}</ListItem>
                        <ListItem>Description: {quiz.description}</ListItem>
                        <ListItem>Created By: {quiz.createdBy}</ListItem>
                        <ListItem>Category By: {quiz.category}</ListItem>
                    </List>
                </Stack>
            </Box>
        </Link>

    );
});

export default QuizSelectionItem;