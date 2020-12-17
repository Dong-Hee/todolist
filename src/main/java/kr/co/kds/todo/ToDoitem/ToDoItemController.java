package kr.co.kds.todo.ToDoitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoItemController {
	
    @Autowired
    private ToDoItemService toDoItemService;

    // 전체조회
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<ToDoItemResponse> getAll() {

        List<String> errors = new ArrayList<>();
        List<ToDoItem> toDoItems = toDoItemService.getAll();
        List<ToDoItemResponse> toDoItemResponses = new ArrayList<>();
        toDoItems.stream().forEach(toDoItem -> {
            toDoItemResponses.add(ToDoItemAdapter.toToDoItemResponse(toDoItem, errors));
        });
        return toDoItemResponses;
    }
    
    // id조회
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ToDoItemResponse get(@PathVariable(value="id") Integer id) {

    	List<String> errors = new ArrayList<>();
        ToDoItem toDoItem = null;
        try {
            toDoItem = toDoItemService.get(id);
        } catch (final Exception e) {
            errors.add(e.getMessage());
        }
        return ToDoItemAdapter.toToDoItemResponse(toDoItem, errors);
    }

    // 등록
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ToDoItemResponse create(@RequestBody final ToDoItemRequest toDoItemRequest) {

    	List<String> errors = new ArrayList<>();
        ToDoItem toDoItem = ToDoItemAdapter.toToDoItem(toDoItemRequest);
        
        try {
            toDoItem = toDoItemService.create(toDoItem);
        } catch (final Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
        }
        return ToDoItemAdapter.toToDoItemResponse(toDoItem, errors);
    }

}
