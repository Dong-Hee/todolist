package kr.co.kds.todo.ToDoitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
}
