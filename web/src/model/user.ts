import { Quiz } from "./quiz";

export type User = {
  id: number;
  first_name: string;
  quizzes?: Quiz[];
};
