import { api } from "./api";
import { IQuizClient, QuizClient } from "./clients/quizClient";
import { IUserClient, UserClient } from "./clients/UserClient";

export const genQuizClient = (): Promise<IQuizClient> => {
    return api(QuizClient)
}

export const genUserClient = (): Promise<IUserClient> => {
    return api(UserClient)
}