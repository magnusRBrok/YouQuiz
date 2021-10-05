import { QuestionOption } from "./questionOption";
import { Question } from "./question";
import { Quiz } from "./quiz";

export type QuizSession = {
  userId: number;
  quiz: Quiz;
  answers: Map<Question, QuestionOption>;
};
