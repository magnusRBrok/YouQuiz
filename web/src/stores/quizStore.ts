import { makeObservable, observable, action, computed } from "mobx";
import { QuestionOption } from "../model/questionOption";
import { Question } from "../model/question";
import { Quiz } from "../model/quiz";
import { QuizSession } from "../model/quizSession";

export class QuizStoreImpl {
  quizes: Quiz[] = [];
  quizSession: QuizSession | undefined;

  constructor() {
    makeObservable(this, {
      quizes: observable,
      quizSession: observable,
      addQuiz: action,
      getQuiz: action,
      newQuizSession: action,
      answerQuestion: action,
      correctAnswers: computed,
    });
  }

  addQuiz(quiz: Quiz) {
    this.quizes.push(quiz);
  }

  getQuiz(id: number) {
    return this.quizes.find((quiz) => quiz.id === id);
  }

  newQuizSession(quiz: Quiz) {
    this.quizSession = {
      userId: 1,
      quiz: quiz,
      answers: new Map<Question, QuestionOption>(),
    };
  }

  answerQuestion(question: Question, answer: QuestionOption) {
    this.quizSession?.answers.set(question, answer);
  }

  get correctAnswers(): number {
    if (!this.quizSession) return 0;
    return Array.from(this.quizSession.answers.values()).reduce(
      (acc, answer) => (answer.isCorrect ? acc + 1 : acc),
      0
    );
  }
}

export const QuizStore = new QuizStoreImpl();

//Create a test quiz. Only temporary for mock data.
const testQuiz1: Quiz = {
  id: 1,
  title: "DevOps quizzen",
  category: "DevOps",
  description: "En quiz om DevOps",
  createdBy: 1,
  questions: [
    {
      description: "Hvilket problem forsøger DevOps at løse?",
      options: [
        {
          text: "Lange deployment cycles",
          isCorrect: false,
        },
        {
          text: "Skrøbelig infrastruktur og applikationskode",
          isCorrect: false,
        },
        {
          text: "Ineffektive eller uddaterede applikationer",
          isCorrect: false,
        },
        {
          text: "Alle ovenstående",
          isCorrect: true,
        },
      ],
    },
    {
      description: "nyt spørgsmål",
      options: [
        {
          text: "test",
          isCorrect: false,
        },
        {
          text: "test2",
          isCorrect: true,
        },
      ],
    },
  ],
};
  const testQuiz2: Quiz = {
    id: 2,
    title: "DevOps quizzen",
    category: "DevOps",
    description: "En quiz om DevOps",
    createdBy: 1,
    questions: [
      {
        description: "Hvilket problem forsøger DevOps at løse?",
        options: [
          {
            text: "Lange deployment cycles",
            isCorrect: false
          },
          {
            text: "Skrøbelig infrastruktur og applikationskode",
            isCorrect: false
          },
          {
            text: "Ineffektive eller uddaterede applikationer",
            isCorrect: false
          },
          {
            text: "Alle ovenstående",
            isCorrect: true
          }
        ]
      },
      {
          description: "nyt spørgsmål",
          options: [
              {
                  text: "test",
                  isCorrect: false
              },
              {
                  text: "test2",
                  isCorrect: true
              }
          ]
      }
    ]
  }
  const testQuiz3: Quiz = {
    id: 3,
    title: "DevOps quizzen",
    category: "DevOps",
    description: "En quiz om DevOps",
    createdBy: 1,
    questions: [
      {
        description: "Hvilket problem forsøger DevOps at løse?",
        options: [
          {
            text: "Lange deployment cycles",
            isCorrect: false
          },
          {
            text: "Skrøbelig infrastruktur og applikationskode",
            isCorrect: false
          },
          {
            text: "Ineffektive eller uddaterede applikationer",
            isCorrect: false
          },
          {
            text: "Alle ovenstående",
            isCorrect: true
          }
        ]
      },
      {
          description: "nyt spørgsmål",
          options: [
              {
                  text: "test",
                  isCorrect: false
              },
              {
                  text: "test2",
                  isCorrect: true
              }
          ]
      }
    ]
  }


  //Add test quiz to store. Only temporary for mock data.
  QuizStore.addQuiz(testQuiz1);
  QuizStore.addQuiz(testQuiz2);
  QuizStore.addQuiz(testQuiz3);