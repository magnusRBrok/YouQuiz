import { Text } from '@chakra-ui/layout';
import { FC } from 'react';
import BasicLayout from '../components/layouts/basicLayout';


const NotFoundPage: FC = () => {

  return (

    <BasicLayout>
     <Text>404 Page not found</Text>
    </BasicLayout>
   
  );
}

export default NotFoundPage;
