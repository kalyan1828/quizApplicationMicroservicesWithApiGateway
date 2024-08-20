package com.quizService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizService.feign.QuizInterface;
import com.quizService.model.QuestionWrapper;
import com.quizService.model.Quiz;
import com.quizService.model.Response;
import com.quizService.repository.QuizDAO;


@Service
public class QuizService {

	@Autowired
	QuizDAO quizDAo;
	@Autowired
	QuizInterface quizInterface;
	//s@Autowired
	//QuestionDAO questionDAO;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions=quizInterface.getQuestionForQuiz(category, numQ).getBody(); 
		// call geerate url http:localhost/8080/question/genertae by using restTemplate
				//questionDAO.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questions);
		quizDAo.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz=quizDAo.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionsIds();
		quizInterface.getQuestionsFromId(questionIds);
		ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromId(questionIds);
		
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		ResponseEntity<Integer> score=quizInterface.getScore(response);
		return score;
	}
	
}
