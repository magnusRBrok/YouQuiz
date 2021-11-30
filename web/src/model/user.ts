import { Quiz } from "./quiz";

export type UserModel = {
  id: number;
  first_name: string;
  quizzes?: Quiz[];
};
