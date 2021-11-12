import { Quiz } from "./quiz";

export type User = {
    id: number,
    firstName: string,
    quizes: Quiz[]
}