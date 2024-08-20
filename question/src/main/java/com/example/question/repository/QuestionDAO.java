package com.example.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.question.model.Questions;

public interface QuestionDAO extends JpaRepository<Questions, Integer> {

	List<Questions> getByCategory(String category);

	@Query(value="select q.id from questions q where q.category=:category order by RAND() Limit :numQ",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);
	

}
