package todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import todo.dto.Todo;
import todo.dto.TodoMember;

@Mapper
public interface TodoMapper {
	int idCheck(String id);
	int signup(TodoMember member);
	TodoMember login(TodoMember member);
	//로그인 위한 정보 : id/pw 
	//로그인 성공시 가져오는 정보 : id/pw 에 해당되는 유저 정보 (SELECT 결과)
	List<Todo> selectTodoList(int todoMemberNo);
	int insert(Todo todo);
	int update(Todo todo);
	int delete(int todoNo);
	
}
