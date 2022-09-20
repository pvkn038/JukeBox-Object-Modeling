package com.crio.codingame.services;

import java.util.List;
import java.util.ArrayList;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.repositories.CRUDRepository;
import com.crio.codingame.repositories.IQuestionRepository;
import com.crio.codingame.repositories.QuestionRepository;
public class QuestionService implements IQuestionService{
    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Question create(String title, Level level, Integer difficultyScore) {
     final Question question = new Question(title,level, difficultyScore);
        return questionRepository.save(question);
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Get All Questions if level is not specified.
    // Or
    // Get List of Question which matches the level provided.

    @Override
    public List<Question> getAllQuestionLevelWise(Level level) {
        List<Question> matchesLevelList = new ArrayList<Question>();

        List<Question> levelList = questionRepository.findAllQuestionLevelWise(level);
        
        if(level == null){
       
         List<Question> forNullLevel =  questionRepository.findAll();
         return forNullLevel;
   
        }
        
        for(Question question : levelList){
   
           matchesLevelList.add(new Question(
               question.getId(),
               question.getTitle(),
               question.getLevel(),
               question.getScore())
            
           );
        }
   
   
        return matchesLevelList;
       
    }
    
}
