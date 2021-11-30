import { Quiz } from "../../model/quiz";
import { AuthBase, ClientBase } from "../clientBase";

export interface IQuizClient {
  getQuiz: (id: number) => Promise<Quiz>;
  createQuiz: (quiz: Quiz) => Promise<Quiz>;
  updateQuiz: (id: number, quiz: Quiz) => Promise<Quiz>;
  deleteQuiz: (id: number) => Promise<void>;
}

export class QuizClient extends ClientBase implements IQuizClient {
  private baseUrl?: string;

  constructor(configuration: AuthBase, baseUrl?: string) {
    super(configuration);
    // if client constructor is called without a baseUrl, localhost:8080 will be used
    this.baseUrl =
      baseUrl !== undefined && baseUrl !== null
        ? baseUrl
        : process.env.REACT_APP_API_URL;
  }

  getQuiz = async (id: number): Promise<Quiz> => {
    const url = `${this.baseUrl}/rest/quiz/${id}`;
    const options: RequestInit = {};

    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        // TODO handle the returned JSON object in next chain
        .then((res) => {
          console.log(res);
          return res as Quiz;
        })
    );
  };

  getAllQuizzes = async (): Promise<Quiz[]> => {
    const url = `${this.baseUrl}/rest/quiz`;
    const options: RequestInit = {};

    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        // TODO handle the returned JSON object in next chain
        .then((res) => {
          console.log(res as Quiz[]);
          return res as Quiz[];
        })
    );
  };

  createQuiz = async (quiz: Quiz): Promise<Quiz> => {
    const url = `${this.baseUrl}/rest/quiz`;
    const options: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(quiz),
    };
    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        .catch((e) => console.log("ERROR", e))
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  createRandomQuiz = async (
    title: String,
    description?: string,
    limit?: number,
    category?: string,
    difficulty?: string
  ): Promise<number> => {
    const url = `${this.baseUrl}/rest/quiz/random`;
    const options: RequestInit = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: title,
        description: description,
        questionLimit: limit,
        category: category,
        difficulty: difficulty,
      }),
    };
    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        .catch((e) => {
          console.log("ERROR", e);
          throw e;
        })
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  updateQuiz = async (id: number, quiz: Quiz): Promise<Quiz> => {
    const url = `${this.baseUrl}/rest/quiz`;
    const options: RequestInit = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(quiz),
    };
    return (
      this.transformOptions(options)
        .then((transformedOptions_) => fetch(url, transformedOptions_))
        .then((response: Response) => this.processResponse(response))
        .catch((e) => console.log("ERROR", e))
        // TODO handle the returned JSON object in next chain
        .then()
    );
  };

  deleteQuiz = async (id: number): Promise<void> => {
    const url = `${this.baseUrl}/rest/quiz/${id}`;
    const options: RequestInit = {
      method: "DELETE",
    };
    return this.transformOptions(options)
      .then((transformedOptions_) => fetch(url, transformedOptions_))
      .then((response: Response) => this.processResponse<void>(response))
      .catch((e) => console.log("ERROR", e));
  };
}
