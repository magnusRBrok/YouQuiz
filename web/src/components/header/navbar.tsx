import { HStack, Text } from '@chakra-ui/layout';
import { Link } from 'react-router-dom';
import { FC } from 'react';

const NavBar: FC = ({ children }) => {

    return (
        <nav>
            <HStack spacing={5}>
                {/* TODO: Actual navigation links */}
                <Link to={"/"} ><Text>Home</Text></Link>
                <Link to={"/quiz"}><Text>Start Quiz</Text></Link>
                {/* <Link>Link</Link> */}
            </HStack>
        </nav>
    );
}

export default NavBar;