package com.testing.questions_history.service;

import com.testing.questions_history.QuestionNotFoundException;
import com.testing.questions_history.dto.AnswerForQuiz;
import com.testing.questions_history.dto.CompleteResult;
import com.testing.questions_history.dto.ResultAnswer;
import com.testing.questions_history.model.Question;
import com.testing.questions_history.model.QuestionWrapper;
import com.testing.questions_history.model.Quiz;
import com.testing.questions_history.repository.QuestionRepository;
import com.testing.questions_history.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;

    private final QuizRepository quizRepository;

    public Quiz createQuiz(String quizTitle, int categoryId) {
        List<Question> questionList =  questionRepository.findRandomQuestionsByCategory(categoryId, 10);
        Quiz quiz = new Quiz();
        quiz.setTitle(quizTitle);
        quiz.setQuestions(questionList);
        quizRepository.save(quiz);
        return quiz;
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) throws QuestionNotFoundException {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for (Question q : questionFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(),
                        q.getOption2(), q.getOption3(), q.getOption4(), q.getRight_answer());
                questionsForUser.add(qw);
            }

            return questionsForUser;
        }
        throw new QuestionNotFoundException("Could not find any quiz with ID " + id);
    }

    public CompleteResult getResultAnswer(List<QuestionWrapper> questionsForUser, AnswerForQuiz answer) {
        List<ResultAnswer> resultAnswers = new ArrayList<>();
        List<String> resultList = List.of(answer.answer1(), answer.answer2(), answer.answer3(),
                                answer.answer4(), answer.answer5(), answer.answer6(), answer.answer7(),
                                answer.answer8(), answer.answer9(), answer.answer10());
        int result = 0;
        int i = 0;
        boolean ch;
        for (int j = 0; j < 10; j++) {
            String right_answer = questionsForUser.get(i).getRight_answer();
            String user_answer = resultList.get(i);
            ch = user_answer.equals(right_answer);
            ResultAnswer answerTemp = new ResultAnswer(questionsForUser.get(i).getQuestion_title(),
                    right_answer, user_answer, ch);
            resultAnswers.add(answerTemp);
            if (ch) result++;
            i++;
        }
        return new CompleteResult(result, resultAnswers);
    }
}
