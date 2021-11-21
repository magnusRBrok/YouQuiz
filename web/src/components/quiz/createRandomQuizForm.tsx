import { Button } from "@chakra-ui/button";
import {
  FormControl,
  FormErrorMessage,
  FormLabel,
} from "@chakra-ui/form-control";
import { Input } from "@chakra-ui/input";
import { Stack } from "@chakra-ui/layout";
import {
  NumberDecrementStepper,
  NumberIncrementStepper,
  NumberInput,
  NumberInputField,
  NumberInputStepper,
} from "@chakra-ui/number-input";
import { Select } from "@chakra-ui/select";
import { FC, useState } from "react";
import { useForm } from "react-hook-form";
import { AuthBase } from "../../services/clientBase";
import { QuizClient } from "../../services/clients/quizClient";
import { QuizStore } from "../../stores/quizStore";

interface Props {
  setSubmitting: (isSubmitting: boolean) => void;
}

const CreateRandomQuizForm: FC<Props> = ({ setSubmitting }) => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const [creationFailed, setCreationFailed] = useState(false);

  const onSubmit = async (data: any) => {
    setCreationFailed(false);
    setSubmitting(true);
    const { title, description, limit, category, difficulty } = data;
    console.log(data);
    try {
      let client = new QuizClient(new AuthBase(""));
      await client.createRandomQuiz(
        title,
        description,
        limit,
        category,
        difficulty
      );
      let quizzes = await client.getAllQuizzes();
      QuizStore.setQuizzes(quizzes);
    } catch (e) {
      setCreationFailed(true);
    }
    setSubmitting(false);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Stack spacing="4">
        <FormControl isInvalid={errors.title}>
          <FormLabel htmlFor="title">Title *</FormLabel>
          <Input
            id="title"
            {...register("title", { required: true })}
            placeholder="Quiz title..."
          />
          <FormErrorMessage>
            Please enter a title for the quiz.
          </FormErrorMessage>
        </FormControl>
        <FormControl isInvalid={errors.description}>
          <FormLabel htmlFor="description">Description</FormLabel>
          <Input
            id="description"
            {...register("description", {})}
            placeholder="Quiz description..."
          />
        </FormControl>
        <FormControl isInvalid={errors.limit}>
          <FormLabel htmlFor="limit">Max number of questions</FormLabel>
          <NumberInput defaultValue={10} min={0} max={20}>
            <NumberInputField id="limit" {...register("limit", {})} />
            <NumberInputStepper>
              <NumberIncrementStepper />
              <NumberDecrementStepper />
            </NumberInputStepper>
          </NumberInput>
        </FormControl>
        <FormControl isInvalid={errors.category}>
          <FormLabel htmlFor="category">Category</FormLabel>
          <Select
            placeholder="Random"
            id="category"
            {...register("category", {})}
          >
            <option value="devops">Devops</option>
            <option value="linux">Linux</option>
            <option value="bash">Bash</option>
            <option value="php">PHP</option>
            <option value="docker">Docker</option>
            <option value="html">HTML</option>
            <option value="mysql">MySQL</option>
            <option value="wordpress">Wordpress</option>
            <option value="laravel">Laravel</option>
            <option value="kubernetes">Kubernetes</option>
            <option value="javascript">JavaScript</option>
          </Select>
        </FormControl>
        <FormControl isInvalid={errors.difficulty || creationFailed}>
          <FormLabel htmlFor="difficulty">Difficulty</FormLabel>
          <Select
            placeholder="Random"
            id="difficulty"
            {...register("difficulty", {})}
          >
            <option value="easy">Easy</option>
            <option value="medium">Medium</option>
            <option value="hard">Hard</option>
          </Select>
          <FormErrorMessage>
            Creation of quiz failed. Please try selecting a different
            difficulty.
          </FormErrorMessage>
        </FormControl>
        <Button type="submit" colorScheme="blue" size="lg" fontSize="md">
          Generate quiz
        </Button>
      </Stack>
    </form>
  );
};

export default CreateRandomQuizForm;
