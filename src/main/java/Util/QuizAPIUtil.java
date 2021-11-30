package Util;

import Quiz.model.Question;
import Quiz.model.QuestionOption;
import Quiz.model.Quiz;
import QuizAPIClient.QuizApiQuestionDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuizAPIUtil {

    public static Question convertQuestion(QuizApiQuestionDto dto) {
        Question question = new Question();
        question.setDescription(dto.getQuestion());

        Collection<QuestionOption> options = new ArrayList<>();
        for(String q : dto.getAnswers().keySet()) {
            if (dto.getAnswers().get(q) == null) continue;
            QuestionOption option = new QuestionOption();
            option.setText(dto.getAnswers().get(q));
            option.setCorrect(dto.getCorrect_answers().get(q + "_correct"));
            option.setQuestion(question);
            options.add(option);
        }
        question.setOptions(options);
        return question;
    }

    public static Quiz convertQuiz(List<QuizApiQuestionDto> dtos) {
        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();
        dtos.forEach(dto -> {
            Question question = convertQuestion(dto);
            question.setQuiz(quiz);
            questions.add(question);
        });
        quiz.setQuestions(questions);
        return quiz;
    }

}
