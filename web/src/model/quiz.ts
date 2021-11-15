import { Question } from "./question";
import { User } from "./user";

export type Quiz = {
  id: number;
  title?: string;
  category?: string;
  description?: string;
  createdBy?: User; // user ID
  createdById: number;
  questions: Question[];
};
