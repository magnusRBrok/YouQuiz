import { Heading, Stack } from "@chakra-ui/react";
import { observer } from "mobx-react-lite";
import { FC, useCallback } from "react";
import { Question } from "../../model/question";
import { QuestionOption } from "../../model/questionOption";
import { QuizStore } from "../../stores/quizStore";
import Option from "./optionComponent";

interface questionProp {
  question: Question;
}

const QuestionComponent: FC<questionProp> = observer(({ question }) => {
  const answer = QuizStore.quizSession?.answers.get(question);

  const handleAnswer = useCallback(
    (option: QuestionOption) => {
      !answer && QuizStore.answerQuestion(question, option);
    },
    [question, answer]
  );

  return (
    <div>
      <Heading size="md">{question.description}</Heading>
      <Stack spacing={4}>
        {question.options.map((option, i) => (
          <Option
            option={option}
            answer={answer}
            onClick={handleAnswer}
            i={i}
          />
        ))}
      </Stack>
    </div>
  );
});

export default QuestionComponent;
