package com.testing.questions_history.repository;

import com.testing.questions_history.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Long countById(Integer id);

    @Query(value = "SELECT DISTINCT * FROM question q WHERE q.category_id=:categoryId ORDER BY RAND() LIMIT :numQ",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("categoryId") int categoryId, @Param("numQ") int numQ);

    List<Question> findAllByCategory_Id(Integer id);
}
