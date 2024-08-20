package com.quizService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quizService.model.Quiz;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
