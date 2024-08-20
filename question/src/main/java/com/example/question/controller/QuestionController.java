package com.example.question.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.question.model.QuestionWrapper;
import com.example.question.model.Questions;
import com.example.question.model.Response;
import com.example.question.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Questions>>getAllQuestios() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{cat}")
	public ResponseEntity<List<Questions>> getAllQuestionsByCategory(@PathVariable("cat") String category){
		return questionService.getAllQuestionsByCAtegory(category);
		
	}
	
	@PostMapping("addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Questions questions) {
		
		return questionService.addQuestion(questions);	
	}
	//generate
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer numQuestions) {
		
		
		return questionService.getQuestionForQuiz(category, numQuestions);
		
	}
	
	//getQuestions(questionID)
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		
		return questionService.getQuestionsFromId(questionIds);
	 
	}
	//getScore
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response>responses){
		return questionService.getScore(responses);
	}
}

