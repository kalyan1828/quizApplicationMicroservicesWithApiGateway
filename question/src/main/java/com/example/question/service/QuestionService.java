package com.example.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.question.model.QuestionWrapper;
import com.example.question.model.Questions;
import com.example.question.model.Response;
import com.example.question.repository.QuestionDAO;


@Service
public class QuestionService {
	@Autowired
	QuestionDAO questionDAO;
	
	public ResponseEntity<List<Questions>> getAllQuestions() {
		
		try{
			return new ResponseEntity<>(questionDAO.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Questions>> getAllQuestionsByCAtegory(String category) {
		try {
		return new ResponseEntity<>(questionDAO.getByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Questions questions) {
		try {
		questionDAO.save(questions);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("sucess",HttpStatus.CREATED);
	}

	public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQuestions) {
		List<Integer> questions=questionDAO.findRandomQuestionsByCategory(category,numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Questions> questions=new ArrayList<>();
		
		for(Integer id:questionIds) {
			questions.add(questionDAO.findById(id).get());
		}
		for(Questions question:questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int right=0;
		for(Response response:responses) {
			Questions question=questionDAO.findById(response.getId()).get();
			if(response.getRes().equals(question.getRightAnswer())) {
			right++;
			}
			
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
