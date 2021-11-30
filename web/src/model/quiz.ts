import { Question } from "./question";
import { UserModel } from "./user";

export type Quiz = {
  id: number;
  title?: string;
  category?: string;
  description?: string;
  createdBy?: UserModel; // user ID
  createdById: number;
  questions: Question[];
};
